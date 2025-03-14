plugins {
    `java-conventions`
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(projects.events)
    implementation(projects.toolingCore)
    implementation(libs.picocli)
    implementation(libs.slf4j.api)
    compileOnlyApi(libs.apiguardian)
    runtimeOnly(libs.log4j.slf4j2.impl)
}

tasks {
    compileJava {
        options.release = 21
        options.javaModuleMainClass = "org.opentest4j.reporting.cli.ReportingCli"
    }
    jar {
        manifest {
            attributes("Main-Class" to "org.opentest4j.reporting.cli.ReportingCli")
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
