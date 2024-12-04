plugins {
    `java-conventions`
}

dependencies {
    annotationProcessor(libs.immutables.value)
    compileOnlyApi(libs.apiguardian)
    compileOnly(libs.immutables.valueAnnotations)
}

tasks {
    compileJava {
        options.compilerArgs.add("-Aimmutables.gradle.incremental")
    }
}
