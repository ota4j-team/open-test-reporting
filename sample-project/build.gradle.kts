import org.gradle.api.tasks.PathSensitivity.NONE
import java.nio.file.Files

plugins {
    `java-basics`
}

val cli by configurations.dependencyScope("cli")
val cliClasspath by configurations.resolvable("cliClasspath") {
    extendsFrom(cli)
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testImplementation(projects.toolingSpi)

    testRuntimeOnly(libs.junit.platform.reporting)
    testRuntimeOnly(projects.toolingSpi)

    cli(projects.cli)
    cli(libs.junit.platform.reporting)
}

configurations.all {
    resolutionStrategy.dependencySubstitution {
        substitute(module("${group}:open-test-reporting-${projects.toolingSpi.name}"))
            .using(project(projects.toolingSpi.path))
    }
}

val htmlReportFile = tasks.test.flatMap { it.reports.junitXml.outputLocation.file("open-test-report.html") }

tasks {
    compileTestJava {
        options.release = 21
        modularity.inferModulePath = true
    }

    val eventXmlFile =
        test.map { it.reports.junitXml.outputLocation.get().file("open-test-report.xml") }

    val convertTestResultXmlToHierarchicalFormat by registering(JavaExec::class) {
        mainModule = "org.opentest4j.reporting.cli"
        modularity.inferModulePath = true
        args("convert")
        classpath(cliClasspath)
        inputs.file(eventXmlFile).withPathSensitivity(NONE).skipWhenEmpty()
        argumentProviders += CommandLineArgumentProvider {
            listOf(eventXmlFile.get().asFile.absolutePath)
        }
        outputs.files(eventXmlFile.map { it.asFile.resolveSibling("hierarchy.xml") })
        outputs.cacheIf { true }
    }

    val generateHtmlReport by registering(JavaExec::class) {
        mainModule = "org.opentest4j.reporting.cli"
        modularity.inferModulePath = true
        args("html-report")
        classpath(cliClasspath)
        outputs.file(htmlReportFile)
        inputs.files(eventXmlFile).withPathSensitivity(NONE).skipWhenEmpty()
        argumentProviders += CommandLineArgumentProvider {
            listOf(
                "--output",
                htmlReportFile.get().asFile.absolutePath
            ) + eventXmlFile.get().asFile.absolutePath
        }
        outputs.cacheIf { true }
    }

    configurations.consumable("xmlReport") {
        attributes {
            outgoing.artifact(eventXmlFile)
            attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.RESOURCES))
        }
    }

    test {
        useJUnitPlatform()

        ignoreFailures = true

        jvmArgumentProviders += CommandLineArgumentProvider {
            listOf(
                "-Djunit.platform.reporting.open.xml.enabled=true",
                "-Djunit.platform.output.capture.stdout=true",
                "-Djunit.platform.output.capture.stderr=true",
                "-Djunit.platform.reporting.output.dir=${reports.junitXml.outputLocation.get().asFile.absolutePath}"
            )
        }

        doFirst {
            files(reports.junitXml.outputLocation.get().asFileTree.matching {
                include("open-test-report.xml")
                include("open-test-report.html")
                include("hierarchy.xml")
            }).files.forEach {
                Files.delete(it.toPath())
            }
        }

        finalizedBy(convertTestResultXmlToHierarchicalFormat, generateHtmlReport)
    }
}
