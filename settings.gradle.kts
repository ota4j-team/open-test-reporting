plugins {
    id("com.gradle.develocity") version "3.18.2"
    id("com.gradle.common-custom-user-data-gradle-plugin") version "2.0.2"
}

rootProject.name = "open-test-reporting"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots") {
            mavenContent {
                snapshotsOnly()
                includeGroupAndSubgroups("org.junit")
            }
        }
    }
}

val isCiServer = System.getenv("CI") != null

develocity {
    buildScan {
        server = "https://ge.junit.org"
        uploadInBackground = !isCiServer

        obfuscation {
            if (isCiServer) {
                username { "github" }
            } else {
                hostname { null }
                ipAddresses { emptyList() }
            }
        }

        publishing.onlyIf { it.isAuthenticated }
    }
}

buildCache {
    local {
        isEnabled = !isCiServer
    }
    remote(develocity.buildCache) {
        val authenticated = System.getenv("DEVELOCITY_ACCESS_KEY") != null
        isPush = isCiServer && authenticated
    }
}

include("cli")
include("documentation")
include("events")
include("html-report")
include("sample-project")
include("schema")
include("tooling-core")
include("tooling-spi")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
