plugins {
    `java-conventions`
}

dependencies {
    api(projects.schema)
    testImplementation(libs.archunit)
    testImplementation(libs.assertj.core)
    testImplementation(libs.bundles.junit)
    testImplementation(libs.xmlunit.assertj)
}
