plugins {
    `java-conventions`
}

val schemas by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
    outgoing {
        artifact(file("src/main/resources/org/opentest4j/reporting/schema"))
    }
}
