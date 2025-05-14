import org.gradle.api.publish.maven.MavenPublication

plugins {
    `maven-publish`
    signing
    id("com.gradleup.nmcp")
}

val isSnapshot = project.version.toString().contains("SNAPSHOT")
val isContinuousIntegrationEnvironment = System.getenv("CI")?.toBoolean() ?: false

signing {
    useGpgCmd()
    sign(publishing.publications)
    isRequired = !(isSnapshot || isContinuousIntegrationEnvironment)
}

tasks.withType<Sign>().configureEach {
    onlyIf {
        !isSnapshot // Gradle Module Metadata currently does not support signing snapshots
    }
}

publishing {
    publications {
        register<MavenPublication>("maven")
        withType<MavenPublication>().configureEach {
            pom {
                name = provider { artifactId }
                description = provider { "Module \"${project.name}\" of open-test-reporting" }
                url = "https://github.com/ota4j-team/open-test-reporting"
                scm {
                    connection = "scm:git:git://github.com/ota4j-team/open-test-reporting.git"
                    developerConnection = "scm:git:git://github.com/ota4j-team/open-test-reporting.git"
                    url = "https://github.com/ota4j-team/open-test-reporting"
                }
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "marcphilipp"
                        name = "Marc Philipp"
                        email = "marc@junit.org"
                    }
                }
            }
        }
    }
}
