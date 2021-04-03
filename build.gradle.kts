import org.gradle.api.JavaVersion.VERSION_1_8

plugins {
    `java-library`
    id("org.unbroken-dome.xjc") version "2.0.0"
}

java {
    sourceCompatibility = VERSION_1_8
    targetCompatibility = VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    implementation("info.picocli:picocli:4.6.1")
    implementation("jakarta.xml.bind:jakarta.xml.bind-api:3.0.0")
    xjcTool("com.sun.xml.bind:jaxb-xjc:3.0.0")
    xjcTool("com.sun.xml.bind:jaxb-impl:3.0.0")
}

tasks {
    compileJava {
        options.release.set(8)
    }
    test {
        useJUnitPlatform()
    }
}

xjc {
    xjcVersion.set("3.0")
}

sourceSets {
    main {
        xjcTargetPackage.set("org.opentest4j.reporting.xml")
    }
}
