plugins {
    `java-conventions`
}

dependencies {
    api(projects.schema)
    testImplementation(libs.archunit)
    testImplementation(libs.assertj.core)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.xmlunit.assertj)
}
