plugins {
    `java-conventions`
}

dependencies {
    api(projects.schema)
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
