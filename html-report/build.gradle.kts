import com.github.gradle.node.npm.task.NpmTask
import com.github.gradle.node.npm.task.NpxTask
import java.util.*

plugins {
    base
    alias(libs.plugins.node)
}

node {
    download = providers.gradleProperty("openTestReporting.nodeDownload").map { it.toBoolean() }.orElse(true)
    // renovate: datasource=node-version depName=node versioning=node
    version = "24.19.0"
    // renovate: datasource=npm depName=npm
    npmVersion = "12.0.2"
    npmInstallCommand = providers.environmentVariable("CI").map { "ci" }.orElse("install")
}

val distIncludes = arrayOf("public/**", "src/**", "*.html", "*.js", "*.json", "*.ts")

val eslintCheck = tasks.register<NpxTask>("eslintCheck") {
    dependsOn(tasks.npmInstall)
    command = "eslint"
}

val prettierCheck = tasks.register<NpxTask>("prettierCheck") {
    dependsOn(tasks.npmInstall)
    command = "prettier"
    args.addAll(".", "--check")
}

val prettierWrite = tasks.register<NpxTask>("prettierWrite") {
    dependsOn(tasks.npmInstall)
    command = "prettier"
    args.addAll(".", "--check")
}

tasks.check {
    dependsOn(eslintCheck, prettierCheck)
}

val eslintFix = tasks.register<NpxTask>("eslintFix") {
    dependsOn(tasks.npmInstall)
    command = "eslint"
    args.addAll("--fix")
}

val buildVueDist = tasks.register<NpmTask>("buildVueDist") {
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
