import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    `java-library`
    application
}

java {
    sourceCompatibility = VERSION_1_8
    targetCompatibility = VERSION_1_8
}

application {
    mainClass.set("org.opentest4j.reporting.cli.ReportingCli")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("info.picocli:picocli:4.6.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
}

tasks {
    compileJava {
        options.release.set(8)
    }
    test {
        useJUnitPlatform()
    }
}
