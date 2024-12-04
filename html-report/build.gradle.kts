import com.diffplug.gradle.spotless.SpotlessApply
import com.diffplug.gradle.spotless.SpotlessCheck
import com.github.gradle.node.npm.task.NpmTask
import java.util.*

plugins {
    base
    alias(libs.plugins.node)
    id("com.diffplug.spotless")
}

node {
    download = providers.gradleProperty("openTestReporting.nodeDownload").map { it.toBoolean() }.orElse(true)
    version = providers.fileContents(layout.projectDirectory.file(".tool-versions")).asText.map {
        it.substringAfter("nodejs").trim()
    }
    npmInstallCommand = providers.environmentVariable("CI").map { "ci" }.orElse("install")
}

val distIncludes = arrayOf("public/**", "src/**", "*.html", "*.js", "*.json", "*.ts")

spotless {
    format("prettier") {
        target(*distIncludes)

        val prettierVersion: String = resources.text.fromFile("package.json").asReader().useLines { lines ->
            lines.first { it.contains("prettier") }
        }.substringAfter("prettier\": \"").substringBefore("\"")

        prettier(prettierVersion).apply {
            if (node.download.get()) {
                val npmExec = if (System.getProperty("os.name").lowercase(Locale.ROOT).contains("windows")) "/npm.cmd" else "/bin/npm"
                npmExecutable("${tasks.npmSetup.get().npmDir.get()}${npmExec}")
            }
        }
    }
}

val buildVueDist by tasks.registering(NpmTask::class) {
    dependsOn(tasks.npmInstall)
    shouldRunAfter("spotlessPrettierCheck")
    inputs.files(fileTree(node.nodeProjectDir) {
        include(*distIncludes)
        exclude("public/init.js")
    })
    outputs.file(node.nodeProjectDir.file("dist/index.html"))
    outputs.cacheIf { true }
    npmCommand.addAll("run", "build")
}

tasks.named("spotlessPrettier") {
    dependsOn(tasks.npmInstall)
}

configurations.consumable("htmlReportTemplate") {
    outgoing {
        artifact(buildVueDist)
    }
    attributes {
        attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.RESOURCES))
    }
}
