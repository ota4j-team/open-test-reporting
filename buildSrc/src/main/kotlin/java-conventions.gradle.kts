plugins {
    `java-library`
    id("com.diffplug.spotless")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

spotless {
    val spotlessDir = rootProject.file("gradle/spotless")
    val licenseHeaderFile = File(spotlessDir, "apache-license-2.0.java")
    val javaFormatterConfigFile = File(spotlessDir, "eclipse-formatter-settings.xml")

    java {
        licenseHeaderFile(licenseHeaderFile, "(package|import|open|module) ")
        eclipse().configFile(javaFormatterConfigFile)
        trimTrailingWhitespace()
        endWithNewline()
    }
}

tasks {
    compileJava {
        options.release.convention(8)
    }
    compileTestJava {
        options.release.convention(17)
    }
    jar {
        manifest {
            attributes("Automatic-Module-Name" to "org.opentest4j.reporting.${project.name}")
        }
    }
    test {
        useJUnitPlatform()
    }
}
