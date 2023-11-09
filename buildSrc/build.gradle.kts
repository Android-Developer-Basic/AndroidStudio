plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.script.runtime)
}

gradlePlugin {
    plugins {
        create("greeting-plugin") {
            id = "ru.otus.greeting"
            implementationClass = "ru.otus.GreetingPlugin"
        }
    }
}