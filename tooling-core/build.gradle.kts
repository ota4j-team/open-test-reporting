plugins {
    `java-conventions`
}

val htmlReportTemplate = configurations.dependencyScope("htmlReportTemplate")

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
    htmlReportTemplate(projects.htmlReport)
}

tasks {
    compileJava {
        options.release = 11
    }
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
            srcDir(generatedResourcesDir)
        }
    }
}
