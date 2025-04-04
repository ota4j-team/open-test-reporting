plugins {
    `java-conventions`
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(projects.toolingCore)
    implementation(libs.picocli)
    implementation(libs.slf4j.api)
    compileOnlyApi(libs.apiguardian)
    runtimeOnly(libs.log4j.slf4j2.impl)
}

val mainClass = "org.opentest4j.reporting.cli.ReportingCli"

tasks {
    compileJava {
        options.release = 17
    }
    jar {
        doLast(objects.newInstance(UpdateJarAction::class).apply {
            javaLauncher = project.javaToolchains.launcherFor(java.toolchain)
            args.addAll(
                "--file", archiveFile.get().asFile.absolutePath,
                "--main-class", mainClass,
            )
        })
    }
    shadowJar {
        manifest {
            attributes("Main-Class" to mainClass)
        }
        archiveClassifier = "standalone"
        exclude("META-INF/maven/**")
        exclude("META-INF/LICENSE*")
        exclude("META-INF/NOTICE*")
        exclude("**/module-info.class")
    }
    assemble {
        dependsOn(shadowJar)
    }
}
