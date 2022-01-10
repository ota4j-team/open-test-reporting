plugins {
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0"
}

nexusPublishing {
    packageGroup.set("org.opentest4j")
    repositories {
        sonatype()
    }
}
