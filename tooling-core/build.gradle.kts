import com.github.gradle.node.npm.task.NpmTask

plugins {
    `java-conventions`
    alias(libs.plugins.node)
}

dependencies {
    api(projects.schema)
    implementation(projects.events)
    implementation(projects.toolingSpi)
    implementation(libs.jackson.dataformat.xml)
    implementation(libs.jsonSanitizer)
    implementation(libs.joox)
    testImplementation(libs.assertj.core)
    testImplementation(libs.bundles.junit)
    testImplementation(libs.xmlunit.assertj)
    testCompileOnly(libs.jetbrains.annotations)
}

tasks {
    compileJava {
        options.release = 11
    }
}

node {
    nodeProjectDir = layout.projectDirectory.dir("../html-report")
    download = true
    version = provider {
        resources.text.fromFile("../.tool-versions").asString().substringAfter("nodejs ").trim()
    }
    npmInstallCommand = providers.environmentVariable("CI").map { "ci" }.orElse("install")
}

val buildVueDist by tasks.registering(NpmTask::class) {
    dependsOn(tasks.npmInstall)
    inputs.files(fileTree(node.nodeProjectDir) {
        include("public/**", "src/**", "*.html", "*.js", "*.json", "*.ts")
    })
    outputs.dir(node.nodeProjectDir.dir("dist"))
    npmCommand.addAll("run", "build")
}

val generatedResourcesDir = layout.buildDirectory.dir("generated-resources/main")

val prepareResourceDir by tasks.registering(Sync::class) {
    from(buildVueDist)
    include("index.html")
    rename {
        "template.html"
    }
    into(generatedResourcesDir.map { it.dir("org/opentest4j/reporting/tooling/core/htmlreport") })
}

sourceSets {
    main {
        output.dir(files(generatedResourcesDir).builtBy(prepareResourceDir))
    }
}
