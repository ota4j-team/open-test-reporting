plugins {
    `java-conventions`
}

dependencies {
    api(projects.schema)
    implementation(projects.events)
    testImplementation(libs.assertj.core)
    testImplementation(libs.junit.jupiter)
    testCompileOnly(libs.jetbrains.annotations)
}

tasks {
    compileJava {
        options.release.set(11)
    }
}
