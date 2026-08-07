import com.github.gradle.node.npm.task.NpmTask
import com.github.gradle.node.npm.task.NpxTask
import java.util.*

plugins {
    base
    alias(libs.plugins.node)
}

node {
    download = providers.gradleProperty("openTestReporting.nodeDownload").map { it.toBoolean() }.orElse(true)
    version = "24.19.0"
    npmVersion = "12.0.2"
    npmInstallCommand = providers.environmentVariable("CI").map { "ci" }.orElse("install")
}

val distIncludes = arrayOf("public/**", "src/**", "*.html", "*.js", "*.json", "*.ts")

val eslintCheck by tasks.registering(NpxTask::class) {
    dependsOn(tasks.npmInstall)
    command = "eslint"
}

val prettierCheck by tasks.registering(NpxTask::class) {
    dependsOn(tasks.npmInstall)
    command = "prettier"
    args.addAll(".", "--check")
}

val prettierWrite by tasks.registering(NpxTask::class) {
    dependsOn(tasks.npmInstall)
    command = "prettier"
    args.addAll(".", "--check")
}

tasks.check {
    dependsOn(eslintCheck, prettierCheck)
}

val eslintFix by tasks.registering(NpxTask::class) {
    dependsOn(tasks.npmInstall)
    command = "eslint"
    args.addAll("--fix")
}

val buildVueDist by tasks.registering(NpmTask::class) {
    dependsOn(tasks.npmInstall)
    shouldRunAfter(prettierCheck)
    inputs.files(fileTree(node.nodeProjectDir) {
        include(*distIncludes)
        exclude("public/init.js")
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
