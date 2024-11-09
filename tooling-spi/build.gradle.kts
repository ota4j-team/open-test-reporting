plugins {
    `java-conventions`
}

dependencies {
    annotationProcessor(libs.immutables.value)
    compileOnly(libs.immutables.valueAnnotations)
}

tasks {
    compileJava {
        options.compilerArgs.add("-Aimmutables.gradle.incremental")
    }
}
