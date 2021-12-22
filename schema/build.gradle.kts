plugins {
    `java-conventions`
}

tasks {
    compileJava {
        options.release.set(8)
    }
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "org.opentest4j.reporting.schema")
        }
    }
}
