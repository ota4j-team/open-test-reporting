plugins {
    `java-conventions`
}

val htmlReportTemplate = configurations.dependencyScope("htmlReportTemplate")
val sampleXmlReport = configurations.dependencyScope("sampleXmlReport")
val isFreeBSD = System.getProperty("os.name").contains("FreeBSD", ignoreCase = true)

dependencies {
    api(projects.schema)
    implementation(projects.events)
    implementation(projects.toolingSpi)
    implementation(libs.gson)
    compileOnlyApi(libs.apiguardian)
    compileOnlyApi(libs.jspecify)

    testImplementation(libs.assertj.core)
    testImplementation(libs.bundles.junit)
    testImplementation(libs.xmlunit.assertj)
    testImplementation(libs.playwright)
    testCompileOnly(libs.jetbrains.annotations)

    htmlReportTemplate(projects.htmlReport)
    sampleXmlReport(project(mapOf("path" to projects.sampleProject.path, "configuration" to "xmlReport")))
}

tasks.compileJava {
    options.release = 17
}

val playwrightInstallationAction = objects.newInstance(InstallPlaywright::class).apply {
    classpath.from(configurations.testRuntimeClasspath)
}

val installPlaywright = tasks.register("installPlaywright") {
    doFirst(playwrightInstallationAction)
}

val sampleXmlReportFiles = configurations.resolvable("sampleXmlReportFiles") {
    extendsFrom(sampleXmlReport.get())
}

tasks.test {
    val sampleXmlReportFiles = files(sampleXmlReportFiles)
    inputs.files(sampleXmlReportFiles).withPathSensitivity(PathSensitivity.NONE)
    jvmArgumentProviders.add(CommandLineArgumentProvider {
        listOf("-DsampleXmlReport=${sampleXmlReportFiles.singleFile.absolutePath}")
    })
    if (System.getenv("CI") != null && !isFreeBSD) {
        dependsOn(installPlaywright)
    }
}

abstract class InstallPlaywright @Inject constructor (private val execOperations: ExecOperations) : Action<Task> {
    abstract val classpath: ConfigurableFileCollection
    override fun execute(t: Task) {
        execOperations.javaexec {
            classpath(this@InstallPlaywright.classpath)
            mainClass = "com.microsoft.playwright.CLI"
            args("install", "--with-deps")
        }
    }
}

val htmlReportTemplateFiles = configurations.resolvable("htmlReportTemplateFiles") {
    extendsFrom(htmlReportTemplate.get())
}

val generatedResourcesDir = layout.buildDirectory.dir("generated/sources/htmlReportTemplate")

val prepareResourceDir = tasks.register<Sync>("prepareResourceDir") {
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
