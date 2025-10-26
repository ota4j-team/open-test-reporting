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

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.addAll(listOf("-Xlint:all", "-Werror"))
}
