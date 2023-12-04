plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.23.3")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    compileJava {
        options.release.convention(17)
    }
}
