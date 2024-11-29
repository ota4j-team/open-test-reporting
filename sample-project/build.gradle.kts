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
    testRuntimeOnly(libs.junit.platform.reporting)
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
        options.release = 8
    }

    val eventXmlFiles =
        files(test.map { it.reports.junitXml.outputLocation.get().asFileTree.matching { include("open-test-report.xml") } }).builtBy(test)

    val convertTestResultXmlToHierarchicalFormat by registering(JavaExec::class) {
        mainClass.set("org.opentest4j.reporting.cli.ReportingCli")
        args("convert")
        classpath(cliClasspath)
        inputs.files(eventXmlFiles).withPathSensitivity(NONE).skipWhenEmpty()
        argumentProviders += CommandLineArgumentProvider {
            listOf(eventXmlFiles.singleFile.absolutePath)
        }
    }

    val generateHtmlReport by registering(JavaExec::class) {
        mainClass.set("org.opentest4j.reporting.cli.ReportingCli")
        args("html-report")
        classpath(cliClasspath)
        outputs.file(htmlReportFile)
        inputs.files(eventXmlFiles).withPathSensitivity(NONE).skipWhenEmpty()
        argumentProviders += CommandLineArgumentProvider {
            listOf(
                "--output",
                htmlReportFile.get().asFile.absolutePath
            ) + eventXmlFiles.files.map { it.absolutePath }.toList()
        }
    }

    configurations.consumable("htmlReport") {
        outgoing.artifact(generateHtmlReport.map { it.outputs.files.singleFile })
        attributes {
            attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.RESOURCES))
        }
    }

    test {
        useJUnitPlatform()

        ignoreFailures = true

        jvmArgumentProviders += CommandLineArgumentProvider {
            listOf(
                "-Djunit.platform.reporting.open.xml.enabled=true",
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
