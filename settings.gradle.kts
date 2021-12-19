rootProject.name = "open-test-reporting"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("events")
include("cli")

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
