import org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21

plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(libs.plugins.bnd.markerCoordinates)
    implementation(libs.plugins.spotless.markerCoordinates)
}

tasks {
    compileKotlin {
        compilerOptions.jvmTarget = JVM_21
    }
    compileJava {
        options.release.convention(21)
    }
}

// see https://docs.gradle.org/current/userguide/plugins.html#sec:plugin_markers
val Provider<PluginDependency>.markerCoordinates: Provider<String>
    get() = map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }
