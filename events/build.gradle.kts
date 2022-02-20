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

tasks {
    javadoc {
        (options as StandardJavadocDocletOptions).apply {
            addBooleanOption("Werror", true)
            addBooleanOption("Xdoclint:all", true)
        }
    }
}
