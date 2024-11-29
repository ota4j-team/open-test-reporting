import com.github.gradle.node.npm.task.NpmTask

plugins {
    alias(libs.plugins.node)
}

node {
    download = providers.gradleProperty("openTestReporting.nodeDownload").map { it.toBoolean() }.orElse(true)
    version = providers.fileContents(layout.projectDirectory.file(".tool-versions")).asText.map {
        it.substringAfter("nodejs").trim()
    }
    npmInstallCommand = providers.environmentVariable("CI").map { "ci" }.orElse("install")
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
