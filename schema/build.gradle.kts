plugins {
    `java-conventions`
}

val schemas = configurations.create("schemas") {
    isCanBeConsumed = true
    isCanBeResolved = false
    outgoing {
        artifact(file("src/main/resources/org/opentest4j/reporting/schema"))
    }
}

dependencies {
    compileOnlyApi(libs.apiguardian)
    compileOnlyApi(libs.jspecify)
    testImplementation(libs.bundles.junit)
}
