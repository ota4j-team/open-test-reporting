plugins {
    `java-conventions`
}

dependencies {
    annotationProcessor(libs.immutables.value)
    compileOnly(libs.immutables.valueAnnotations)
    compileOnly(libs.jackson.annotations)
}

tasks {
    compileJava {
        options.compilerArgs.add("-Aimmutables.gradle.incremental")
    }
}
