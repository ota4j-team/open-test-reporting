plugins {
    `java-conventions`
    application
    alias(libs.plugins.shadow)
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
    shadowJar {
        exclude("META-INF/maven/**")
        exclude("META-INF/LICENSE*")
        exclude("META-INF/NOTICE*")
        exclude("**/module-info.class")
    }
}

publishing {
    publications {
        register<MavenPublication>("standalone") {
            project.shadow.component(this)
            artifactId = "${base.archivesName.get()}-standalone"
            pom {
                description = "Standalone CLI distribution of open-test-reporting"
            }
        }
    }
}
