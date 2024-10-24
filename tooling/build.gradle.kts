plugins {
    `java-conventions`
}

dependencies {
    api(projects.schema)
    implementation(projects.events)
    implementation(libs.jackson.dataformat.xml)
    implementation(libs.jsonSanitizer)
    testImplementation(libs.assertj.core)
    testImplementation(libs.bundles.junit)
    testImplementation(libs.xmlunit.assertj)
    testCompileOnly(libs.jetbrains.annotations)
}

tasks {
    compileJava {
        options.release.set(11)
    }
}

val htmlReportProjectDir = layout.projectDirectory.dir("../html-report")

val buildVueDist by tasks.registering {
    doLast {
        exec {
            workingDir = htmlReportProjectDir.asFile
            commandLine("npm", "install")
        }
        exec {
            workingDir = htmlReportProjectDir.asFile
            commandLine("npm", "run", "build")
        }
    }
}

val generatedResourcesDir = layout.buildDirectory.dir("generated-resources/main")

val prepareResourceDir by tasks.registering(Sync::class) {
    dependsOn(buildVueDist)
    from(htmlReportProjectDir.dir("dist"))
    include("index.html")
    rename {
        "template.html"
    }
    into(generatedResourcesDir.map { it.dir("org/opentest4j/reporting/tooling/htmlreport") })
}

sourceSets {
    main {
        output.dir(files(generatedResourcesDir).builtBy(prepareResourceDir))
    }
}
