import net.ltgt.gradle.errorprone.errorprone
import net.ltgt.gradle.nullaway.nullaway

plugins {
    `java-library`
    id("net.ltgt.errorprone")
    id("net.ltgt.nullaway")
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

fun Project.dependencyFromLibs(name: String) =
    the<VersionCatalogsExtension>().named("libs").findLibrary(name).get()

dependencies {
    errorprone(dependencyFromLibs("error-prone-core"))
    errorprone(dependencyFromLibs("nullaway"))
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-Xlint:all")
    options.compilerArgumentProviders.add(CommandLineArgumentProvider {
        if (options.release.orNull == 8) {
            // javac emits an unsuppressable "unknown enum constant ElementType.MODULE" warning when
            // reading JSpecify's @NullMarked with --release 8, so -Werror cannot be used; NullAway is
            // configured with error severity below so nullability violations still fail the build
            listOf("-Xlint:-options")
        }
        else {
            listOf("-Werror")
        }
    })
    options.errorprone {
        if (name == "compileJava") {
            disable(
                "AvoidCommonTypeNames", // The published API contains types named after XML elements (e.g. Throwable).
                "EmptyCatch",
                "EqualsGetClass", // We prefer getClass() over instanceof in equals implementations.
                "StringSplitter", // We don't want to use Guava.
            )
        } else {
            disableAllChecks = true
        }
        nullaway {
            error()
            onlyNullMarked = true
            jspecifyMode = true
            checkContracts = true
            excludedFieldAnnotations.addAll(
                "picocli.CommandLine.Option",
                "picocli.CommandLine.Parameters",
            )
        }
    }
}

tasks.withType<JavaCompile>().named { it.startsWith("compileTest") }.configureEach {
    options.errorprone.nullaway {
        handleTestAssertionLibraries = true
    }
}
