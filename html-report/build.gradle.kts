import com.github.gradle.node.npm.task.NpmTask
import com.github.gradle.node.npm.task.NpxTask

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

val prettierCheck by tasks.registering(NpxTask::class) {
    dependsOn(tasks.npmInstall)
    command = "prettier"
    args.addAll(".", "--check")
}

val prettierWrite by tasks.registering(NpxTask::class) {
    dependsOn(tasks.npmInstall)
    command = "prettier"
    args.addAll(".", "--write")
}

tasks.spotlessCheck {
    dependsOn(prettierCheck)
}

tasks.spotlessApply {
    dependsOn(prettierWrite)
}

val buildVueDist by tasks.registering(NpmTask::class) {
    dependsOn(tasks.npmInstall)
    inputs.files(fileTree(node.nodeProjectDir) {
        include("public/**", "src/**", "*.html", "*.js", "*.json", "*.ts")
    })
    outputs.file(node.nodeProjectDir.file("dist/index.html"))
    outputs.cacheIf { true }
    npmCommand.addAll("run", "build")
}

configurations.consumable("htmlReportTemplate") {
    outgoing {
        artifact(buildVueDist)
    }
    attributes {
        attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.RESOURCES))
    }
}
