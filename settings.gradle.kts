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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
