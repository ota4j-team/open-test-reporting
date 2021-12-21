plugins {
    `java-library`
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}

tasks {
    test {
        useJUnitPlatform()
    }
}
