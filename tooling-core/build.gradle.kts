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

val playwrightInstallationAction = objects.newInstance(InstallPlaywrightDeps::class).apply {
    classpath.from(configurations.testRuntimeClasspath)
}

val installPlaywrightDeps by tasks.registering {
    doFirst(playwrightInstallationAction)
}

val sampleHtmlReportFiles = configurations.resolvable("sampleHtmlReportFiles") {
    extendsFrom(sampleHtmlReport.get())
}

tasks.test {
    inputs.files(sampleHtmlReportFiles).withPathSensitivity(PathSensitivity.NONE)
    jvmArgumentProviders.add(CommandLineArgumentProvider {
        listOf("-DsampleHtmlReport=${sampleHtmlReportFiles.get().singleFile.absolutePath}")
    })
    if (System.getenv("CI") != null) {
        doFirst(playwrightInstallationAction)
    }
}

abstract class InstallPlaywrightDeps @Inject constructor (private val execOperations: ExecOperations) : Action<Task> {
    abstract val classpath: ConfigurableFileCollection
    override fun execute(t: Task) {
        execOperations.javaexec {
            classpath(this@InstallPlaywrightDeps.classpath)
            mainClass = "com.microsoft.playwright.CLI"
            args("install-deps")
        }
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
            srcDir(files(generatedResourcesDir).builtBy(prepareResourceDir))
        }
    }
}
