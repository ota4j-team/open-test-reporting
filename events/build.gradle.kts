plugins {
    `java-conventions`
}

val woodstox = configurations.dependencyScope("woodstox")
val woodstoxRuntimeClasspath = configurations.resolvable("woodstoxRuntimeClasspath") {
    extendsFrom(configurations.testRuntimeClasspath.get())
    extendsFrom(woodstox.get())
}

dependencies {
    api(projects.schema)
    testImplementation(libs.archunit)
    testImplementation(libs.assertj.core)
    testImplementation(libs.bundles.junit)
    testImplementation(libs.xmlunit.assertj)
    woodstox(libs.woodstox.core)
}

tasks {
    val testWoodstox by registering(Test::class) {
        val test by testing.suites.existing(JvmTestSuite::class)
        testClassesDirs = files(test.map { it.sources.output.classesDirs })
        classpath = files(sourceSets.main.map { it.output }) + files(test.map { it.sources.output }) + woodstoxRuntimeClasspath.get()
        group = JavaBasePlugin.VERIFICATION_GROUP
    }
    check {
        dependsOn(testWoodstox)
    }
}
