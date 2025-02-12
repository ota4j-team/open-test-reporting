plugins {
    `java-conventions`
}

val woodstox = configurations.dependencyScope("woodstox")
val woodstoxRuntimeClasspath = configurations.resolvable("woodstoxRuntimeClasspath") {
    extendsFrom(configurations.testRuntimeClasspath.get())
    extendsFrom(woodstox.get())
}

val saxon = configurations.dependencyScope("saxon")
val saxonRuntimeClasspath = configurations.resolvable("saxonRuntimeClasspath") {
    extendsFrom(configurations.testRuntimeClasspath.get())
    extendsFrom(saxon.get())
}

dependencies {
    api(projects.schema)
    compileOnlyApi(libs.apiguardian)
    testImplementation(libs.archunit)
    testImplementation(libs.assertj.core)
    testImplementation(libs.bundles.junit)
    testImplementation(libs.xmlunit.assertj)
    testCompileOnly(libs.jetbrains.annotations)
    woodstox(libs.woodstox.core)
    saxon(libs.saxon)
}

tasks {
    val testWoodstox by registering(Test::class) {
        val test by testing.suites.existing(JvmTestSuite::class)
        testClassesDirs = files(test.map { it.sources.output.classesDirs })
        classpath = files(sourceSets.main.map { it.output }) + files(test.map { it.sources.output }) + woodstoxRuntimeClasspath.get()
        group = JavaBasePlugin.VERIFICATION_GROUP
    }
    val testSaxon by registering(Test::class) {
        val test by testing.suites.existing(JvmTestSuite::class)
        testClassesDirs = files(test.map { it.sources.output.classesDirs })
        classpath = files(sourceSets.main.map { it.output }) + files(test.map { it.sources.output }) + saxonRuntimeClasspath.get()
        group = JavaBasePlugin.VERIFICATION_GROUP
    }
    check {
        dependsOn(testWoodstox, testSaxon)
    }
}
