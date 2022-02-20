plugins {
    id("org.ajoberstar.git-publish") version "4.1.0"
}

val schemas by configurations.creating {
    isCanBeConsumed = false
    isCanBeResolved = true
}

dependencies {
    schemas(project(":schema", "schemas"))
}

gitPublish {
    repoUri.set("https://github.com/ota4j-team/open-test-reporting.git")
    branch.set("gh-pages")

    contents {
        from(schemas)
        into("schemas/snapshot")
    }
}
