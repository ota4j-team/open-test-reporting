plugins {
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

nexusPublishing {
    packageGroup.set("org.opentest4j")
    repositories {
        sonatype()
    }
}
