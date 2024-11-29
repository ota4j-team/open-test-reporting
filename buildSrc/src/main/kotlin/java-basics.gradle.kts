plugins {
    `java-library`
}

normalization {
    runtimeClasspath {
        metaInf {
            ignoreAttribute("Bnd-LastModified")
            ignoreAttribute("Created-By")
            ignoreAttribute("Tool")
        }
    }
}
