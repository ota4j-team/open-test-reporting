import org.gradle.api.tasks.PathSensitivity.NONE
import org.gradle.api.tasks.PathSensitivity.RELATIVE
import java.nio.file.Files

plugins {
    `java-basics`
}

val cli = configurations.dependencyScope("cli").get()
val cliClasspath = configurations.resolvable("cliClasspath") {
    extendsFrom(cli)
}.get()

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
        options.release = 17
        modularity.inferModulePath = true
    }

    val eventXmlFile =
        test.map { it.reports.junitXml.outputLocation.get().file("open-test-report.xml") }

    val convertTestResultXmlToHierarchicalFormat = register<JavaExec>("convertTestResultXmlToHierarchicalFormat") {
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

    val validateTestResultXml = register<JavaExec>("validateTestResultXml") {
        mainModule = "org.opentest4j.reporting.cli"
        modularity.inferModulePath = true
        args("validate")
        classpath(cliClasspath)
        inputs.file(eventXmlFile).withPathSensitivity(NONE).skipWhenEmpty()
        argumentProviders += CommandLineArgumentProvider {
            listOf(eventXmlFile.get().asFile.absolutePath)
        }
    }

    val generateHtmlReport = register<JavaExec>("generateHtmlReport") {
        mainModule = "org.opentest4j.reporting.cli"
        modularity.inferModulePath = true
        args("html-report")
        classpath(cliClasspath)
        outputs.file(htmlReportFile)
        inputs.files(eventXmlFile).withPathSensitivity(RELATIVE).skipWhenEmpty()
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
        environment = environment.filterKeys { it != "TESTLENS_PROJECT_ID" }

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

        finalizedBy(validateTestResultXml, convertTestResultXmlToHierarchicalFormat, generateHtmlReport)
    }
}
