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
    jar {
        // Avoid Immutables annotation from leaking into JAR
        exclude("org/opentest4j/reporting/tooling/spi/htmlreport/package-info.class")
    }
}
