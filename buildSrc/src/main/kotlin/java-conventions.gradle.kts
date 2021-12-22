plugins {
    `java-library`
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}

tasks {
    compileJava {
        options.release.convention(8)
    }
    test {
        useJUnitPlatform()
    }
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "org.opentest4j.reporting.${project.name}")
        }
    }
}
