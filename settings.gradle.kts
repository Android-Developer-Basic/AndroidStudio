pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
    }
    versionCatalogs {
        // declares an additional catalog
        create("androidx") {
            from(files("gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "StudioApp"
include(":app")
 