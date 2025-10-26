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
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(listOf("-Xlint:all", "-Werror"))
}
