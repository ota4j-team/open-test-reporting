plugins {
    `java-conventions`
}

dependencies {
    api(projects.schema)
    implementation(projects.events)
    testImplementation(libs.assertj.core)
    testImplementation(libs.bundles.junit)
    testImplementation(libs.xmlunit.assertj)
    testCompileOnly(libs.jetbrains.annotations)
}

tasks {
    compileJava {
        options.release.set(11)
    }
    test {
        javaLauncher = project.javaToolchains.launcherFor {
            languageVersion = JavaLanguageVersion.of(22)
        }
    }
}
