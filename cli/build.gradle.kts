plugins {
    `java-conventions`
    application
}

application {
    mainClass.set("org.opentest4j.reporting.cli.ReportingCli")
}

dependencies {
    implementation(libs.picocli)
    implementation(projects.events)
}

tasks {
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "org.opentest4j.reporting.cli")
        }
    }
}
