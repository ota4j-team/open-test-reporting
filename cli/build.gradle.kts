plugins {
    `java-conventions`
    application
}

application {
    applicationName = "open-test-reporting"
    mainClass = "org.opentest4j.reporting.cli.ReportingCli"
}

dependencies {
    implementation(projects.events)
    implementation(projects.tooling)
    implementation(libs.picocli)
    implementation(libs.slf4j.api)
    runtimeOnly(libs.log4j.slf4j.impl)
}

tasks {
    compileJava {
        options.release = 11
    }
}
