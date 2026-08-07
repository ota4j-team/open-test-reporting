import org.gradle.api.tasks.PathSensitivity.NONE
import java.nio.file.Files

plugins {
    id("biz.aQute.bnd.builder")
    id("com.diffplug.spotless")
    id("java-basics")
    id("publishing-conventions")
}

base {
    archivesName = "open-test-reporting-${project.name}"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
    withSourcesJar()
    withJavadocJar()
}

spotless {
    val spotlessDir = rootProject.file("gradle/spotless")
    val licenseHeaderFile = File(spotlessDir, "apache-license-2.0.java")
    val javaFormatterConfigFile = File(spotlessDir, "eclipse-formatter-settings.xml")

    java {
        licenseHeaderFile(licenseHeaderFile, "(package|import|open|module) ")
        val fullVersion = requiredVersionFromLibs("eclipse")
        val majorMinorVersion = "([0-9]+\\.[0-9]+).*".toRegex().matchEntire(fullVersion)!!.let { it.groups[1]!!.value }
        eclipse(majorMinorVersion).configFile(javaFormatterConfigFile)
        trimTrailingWhitespace()
        endWithNewline()
    }

    format("moduleDescriptor") {
        target(fileTree(layout.projectDirectory.dir("src/module")) {
            include("**/module-info.java")
        })
        licenseHeaderFile(licenseHeaderFile, "^$")
        trimTrailingWhitespace()
        endWithNewline()
    }
}

fun Project.requiredVersionFromLibs(name: String) =
    libsVersionCatalog.findVersion(name).get().requiredVersion

fun Project.dependencyFromLibs(name: String) =
    libsVersionCatalog.findLibrary(name).get()

private val Project.libsVersionCatalog: VersionCatalog
    get() = the<VersionCatalogsExtension>().named("libs")

val cli = configurations.create("cli") {
    isCanBeResolved = true
    isCanBeConsumed = false
}

dependencies {
    cli(project(":cli"))
    cli(dependencyFromLibs("junit-platform-reporting"))
}

configurations {
    all {
        resolutionStrategy.dependencySubstitution {
            substitute(module("${group}:open-test-reporting-tooling-spi"))
                .using(project(":tooling-spi"))
        }
    }
    compileClasspath {
        attributes {
            attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.JAR))
        }
    }
}

tasks {
    compileJava {
        options.release.convention(8)
        options.javaModuleVersion.convention(provider { project.version.toString() })
    }
    compileTestJava {
        options.release.convention(17)
    }
    val moduleName = "org.opentest4j.reporting.${project.name.replace('-', '.')}"
    val compileModule = register<JavaCompile>("compileModule") {
        val moduleSrcDir = file("src/module/java")
        source(moduleSrcDir)
        destinationDirectory.set(layout.buildDirectory.dir("classes/java/modules"))
        classpath = configurations.compileClasspath.get()
        inputs.property("moduleName", moduleName)
        inputs.property("moduleVersion", project.version)
        options.release = compileJava.flatMap { it.options.release }.map { if (it > 9) it else 9 }
        options.compilerArgs = listOf(
            "--module-version", project.version as String,
            "--module-path", classpath.asPath,
            "--module-source-path", moduleSrcDir.toString(),
            "--patch-module", "$moduleName=${files(sourceSets.main.get().allJava.srcDirs).asPath}",
            "--module", moduleName,
        )
    }
    jar {
        from(files(compileModule.map { it.destinationDirectory.dir(moduleName) })) {
            include("module-info.class")
        }
        manifest {
            attributes(
                "Bundle-Name" to project.name,
                "Bundle-Description" to project.name,
                "Bundle-DocURL" to "https://github.com/ota4j-team/open-test-reporting",
                "Bundle-Vendor" to "opentest4j.org",
                "Bundle-SymbolicName" to moduleName,
            )
        }
        bundle {
            properties.empty() // see https://github.com/bndtools/bnd/tree/master/gradle-plugins#gradle-configuration-cache-support
            bnd(
                "-exportcontents: org.opentest4j.reporting.*",
                "Import-Package: org.apiguardian.*;resolution:=\"optional\",org.jspecify.*;resolution:=\"optional\",*",
            )
        }
    }
    javadoc {
        (options as StandardJavadocDocletOptions).apply {
            addBooleanOption("Werror", true)
            addBooleanOption("Xdoclint:all", true)
        }
    }
    tasks.withType<Jar>().configureEach {
        from(rootDir) {
            include("LICENSE.md")
            into("META-INF")
        }
    }

    val eventXmlFile =
        test.map { it.reports.junitXml.outputLocation.get().file("open-test-report.xml") }
    val htmlReportFile =
        test.map { it.reports.junitXml.outputLocation.get().file("open-test-report.html") }

    val convertTestResultXmlToHierarchicalFormat = register<JavaExec>("convertTestResultXmlToHierarchicalFormat") {
        mustRunAfter(test)
        mainClass.set("org.opentest4j.reporting.cli.ReportingCli")
        args("convert")
        classpath(cli)
        inputs.files(eventXmlFile).withPathSensitivity(NONE).skipWhenEmpty()
        argumentProviders += CommandLineArgumentProvider {
            listOf(eventXmlFile.get().asFile.absolutePath)
        }
        outputs.files(eventXmlFile.map { it.asFile.resolveSibling("hierarchy.xml") })
        outputs.cacheIf { true }
    }

    val generateHtmlReport = register<JavaExec>("generateHtmlReport") {
        mustRunAfter(test)
        mainClass.set("org.opentest4j.reporting.cli.ReportingCli")
        args("html-report")
        classpath(cli)
        inputs.files(eventXmlFile).withPathSensitivity(NONE).skipWhenEmpty()
        argumentProviders += CommandLineArgumentProvider {
            listOf(eventXmlFile.get().asFile.absolutePath)
        }
        outputs.files(htmlReportFile)
        outputs.cacheIf { true }
    }

    withType<Test>().configureEach {
        useJUnitPlatform()
    }

    test {
        jvmArgumentProviders += CommandLineArgumentProvider {
            listOf(
                "-Djunit.platform.reporting.open.xml.enabled=true",
                "-Djunit.platform.reporting.open.xml.git.enabled=true",
                "-Djunit.platform.output.capture.stdout=true",
                "-Djunit.platform.output.capture.stderr=true",
                "-Djunit.platform.reporting.output.dir=${reports.junitXml.outputLocation.get().asFile.absolutePath}"
            )
        }

        doFirst {
            reports.junitXml.outputLocation.get().asFileTree.matching {
                include("open-test-report.xml")
                include("open-test-report.html")
                include("hierarchy.xml")
            }.files.forEach {
                Files.delete(it.toPath())
            }
        }

        finalizedBy(convertTestResultXmlToHierarchicalFormat, generateHtmlReport)
    }
}

configure<PublishingExtension> {
    publications {
        named<MavenPublication>("maven") {
            from(components["java"])
            artifactId = base.archivesName.get()
        }
    }
}
