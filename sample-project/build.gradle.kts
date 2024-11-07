import org.gradle.api.tasks.PathSensitivity.NONE
import java.nio.file.Files

plugins {
    `java-library`
}

val cli by configurations.dependencyScope("cli")
val cliClasspath by configurations.resolvable("cliClasspath") {
    extendsFrom(cli)
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.reporting)
    cli(projects.cli)
}

tasks {
    compileTestJava {
        options.release = 8
    }

    val eventXmlFiles =
        files(test.map { it.reports.junitXml.outputLocation.get().asFileTree.matching { include("junit-platform-events-*.xml") } })

    val convertTestResultXmlToHierarchicalFormat by registering(JavaExec::class) {
        mustRunAfter(test)
        mainClass.set("org.opentest4j.reporting.cli.ReportingCli")
        args("convert")
        classpath(cliClasspath)
        inputs.files(eventXmlFiles).withPathSensitivity(NONE).skipWhenEmpty()
        argumentProviders += CommandLineArgumentProvider {
            listOf(eventXmlFiles.singleFile.absolutePath)
        }
    }

    val generateHtmlReport by registering(JavaExec::class) {
        mustRunAfter(test)
        mainClass.set("org.opentest4j.reporting.cli.ReportingCli")
        args("html-report")
        classpath(cliClasspath)
        inputs.files(eventXmlFiles).withPathSensitivity(NONE).skipWhenEmpty()
        argumentProviders += CommandLineArgumentProvider {
            listOf(eventXmlFiles.singleFile.absolutePath)
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
            // Delete existing event-based report files
            eventXmlFiles.files.forEach {
                Files.delete(it.toPath())
            }
        }

        finalizedBy(convertTestResultXmlToHierarchicalFormat, generateHtmlReport)
    }
}
