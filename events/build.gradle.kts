plugins {
    `java-conventions`
}

dependencies {
    testImplementation(libs.archunit)
}

tasks {
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "org.opentest4j.reporting.events")
        }
    }
}
