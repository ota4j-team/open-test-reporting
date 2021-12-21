plugins {
    `java-conventions`
    application
}

application {
    mainClass.set("org.opentest4j.reporting.cli.ReportingCli")
}

dependencies {
    implementation(projects.events)
    implementation(libs.picocli)
    implementation(libs.slf4j.api)
    runtimeOnly(libs.slf4j.simple)
}

tasks {
    compileJava {
        options.release.set(11)
    }
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "org.opentest4j.reporting.cli")
        }
    }
}
