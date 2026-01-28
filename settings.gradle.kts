plugins {
    id("com.gradle.develocity") version "4.3.2"
    id("com.gradle.common-custom-user-data-gradle-plugin") version "2.4.0"
    id("com.gradleup.nmcp.settings") version "1.4.4"
}

rootProject.name = "open-test-reporting"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
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

nmcpSettings {
    centralPortal {
        username = providers.gradleProperty("mavenCentralUsername")
        password = providers.gradleProperty("mavenCentralPassword")
        publishingType = providers.gradleProperty("mavenCentralPublishingType").orElse("USER_MANAGED")
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
