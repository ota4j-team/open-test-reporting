import org.gradle.api.publish.maven.MavenPublication

plugins {
    `maven-publish`
    signing
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
        create<MavenPublication>("maven") {
            pom {
                name.set(provider { artifactId })
                description.set(provider { "Module \"${project.name}\" of open-test-reporting" })
                url.set("https://github.com/ota4j-team/open-test-reporting")
                scm {
                    connection.set("scm:git:git://github.com/ota4j-team/open-test-reporting.git")
                    developerConnection.set("scm:git:git://github.com/ota4j-team/open-test-reporting.git")
                    url.set("https://github.com/ota4j-team/open-test-reporting")
                }
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("marcphilipp")
                        name.set("Marc Philipp")
                        email.set("marc@junit.org")
                    }
                }
            }
        }
    }
}
