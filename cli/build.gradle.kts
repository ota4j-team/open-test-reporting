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
    implementation(projects.toolingCore)
    implementation(libs.picocli)
    implementation(libs.slf4j.api)
    runtimeOnly(libs.log4j.slf4j2.impl)
}

tasks {
    compileJava {
        options.release = 11
    }
    jar {
        manifest {
            attributes("Main-Class" to application.mainClass)
        }
    }
    shadowJar {
        archiveClassifier = "standalone"
        exclude("META-INF/maven/**")
        exclude("META-INF/LICENSE*")
        exclude("META-INF/NOTICE*")
        exclude("**/module-info.class")
    }
}
