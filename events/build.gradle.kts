plugins {
    `java-conventions`
}

dependencies {
    api(projects.schema)
    testImplementation(libs.archunit)
}
