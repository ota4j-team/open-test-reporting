plugins {
    `java-conventions`
}

val htmlReportTemplate = configurations.dependencyScope("htmlReportTemplate")
val sampleHtmlReport = configurations.dependencyScope("sampleHtmlReport")

dependencies {
    api(projects.schema)
    implementation(projects.events)
    implementation(projects.toolingSpi)
    implementation(libs.gson)
    implementation(libs.joox)

    testImplementation(libs.assertj.core)
    testImplementation(libs.bundles.junit)
    testImplementation(libs.xmlunit.assertj)
    testImplementation(libs.playwright)
    testCompileOnly(libs.jetbrains.annotations)

    htmlReportTemplate(projects.htmlReport)
    sampleHtmlReport(project(mapOf("path" to projects.sampleProject.identityPath, "configuration" to "htmlReport")))
}

tasks.compileJava {
    options.release = 11
}

val installPlaywrightDeps by tasks.registering(JavaExec::class) {
    classpath(configurations.testRuntimeClasspath)
    mainClass = "com.microsoft.playwright.CLI"
    args("install-deps")
}

val sampleHtmlReportFiles = configurations.resolvable("sampleHtmlReportFiles") {
    extendsFrom(sampleHtmlReport.get())
}

tasks.test {
    inputs.files(sampleHtmlReportFiles).withPathSensitivity(PathSensitivity.NONE)
    jvmArgumentProviders.add(CommandLineArgumentProvider {
        listOf("-DsampleHtmlReport=${sampleHtmlReportFiles.get().singleFile.absolutePath}")
    })
}

val htmlReportTemplateFiles = configurations.resolvable("htmlReportTemplateFiles") {
    extendsFrom(htmlReportTemplate.get())
}

val generatedResourcesDir = layout.buildDirectory.dir("generated/sources/htmlReportTemplate")

val prepareResourceDir by tasks.registering(Sync::class) {
    from(htmlReportTemplateFiles)
    rename {
        "template.html"
    }
    into(generatedResourcesDir.map { it.dir("org/opentest4j/reporting/tooling/core/htmlreport") })
}

sourceSets {
    main {
        resources {
            srcDir(files(generatedResourcesDir).builtBy(prepareResourceDir))
        }
    }
}
