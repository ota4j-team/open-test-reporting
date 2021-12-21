plugins {
    `java-conventions`
}

dependencies {
    testImplementation(libs.archunit)
}

tasks {
    compileJava {
        options.release.set(8)
    }
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "org.opentest4j.reporting.events")
        }
    }
}
