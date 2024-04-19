import org.gradle.api.tasks.PathSensitivity.NONE
import java.nio.file.Files

plugins {
    `java-library`
    id("com.diffplug.spotless")
    id("publishing-conventions")
}

base {
    archivesName.set("open-test-reporting-${project.name}")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    withSourcesJar()
    withJavadocJar()
}

spotless {
    val spotlessDir = rootProject.file("gradle/spotless")
    val licenseHeaderFile = File(spotlessDir, "apache-license-2.0.java")
    val javaFormatterConfigFile = File(spotlessDir, "eclipse-formatter-settings.xml")

    java {
        licenseHeaderFile(licenseHeaderFile, "(package|import|open|module) ")
        eclipse().configFile(javaFormatterConfigFile)
        trimTrailingWhitespace()
        endWithNewline()
    }
}

val cli by configurations.creating {
    isCanBeResolved = true
    isCanBeConsumed = false
    isVisible = false
}

dependencies {
    cli(project(":cli"))
}

tasks {
    compileJava {
        options.release.convention(8)
    }
    compileTestJava {
        options.release.convention(17)
    }
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "org.opentest4j.reporting.${project.name}")
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

    val eventXmlFiles =
        files(test.map { it.reports.junitXml.outputLocation.get().asFileTree.matching { include("junit-platform-events-*.xml") } })

    val convertTestResultXmlToHierarchicalFormat by registering(JavaExec::class) {
        dependsOn(test)
        mainClass.set("org.opentest4j.reporting.cli.ReportingCli")
        args("convert")
        classpath(cli)
        inputs.files(eventXmlFiles).withPathSensitivity(NONE).skipWhenEmpty()
        argumentProviders += CommandLineArgumentProvider {
            listOf(eventXmlFiles.singleFile.absolutePath)
        }
    }

    withType<Test>().configureEach {
        useJUnitPlatform()
    }

    test {
        jvmArgumentProviders += CommandLineArgumentProvider {
            listOf(
                "-Djunit.platform.reporting.open.xml.enabled=true",
                "-Djunit.platform.reporting.output.dir=${reports.junitXml.outputLocation.get().asFile.absolutePath}"
            )
        }

        doFirst {
            // Delete existing event-based report files
            eventXmlFiles.files.forEach {
                Files.delete(it.toPath())
            }
        }

        finalizedBy(convertTestResultXmlToHierarchicalFormat)
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
