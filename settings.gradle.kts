rootProject.name = "open-test-reporting"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include("cli")
include("documentation")
include("events")
include("schema")
include("tooling")

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
