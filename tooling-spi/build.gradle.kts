plugins {
    `java-conventions`
}

dependencies {
    annotationProcessor(libs.immutables.value)
    compileOnly(libs.immutables.valueAnnotations)
    implementation(libs.jackson.annotations)
}

tasks {
    compileJava {
        options.compilerArgs.add("-Aimmutables.gradle.incremental")
    }
}
