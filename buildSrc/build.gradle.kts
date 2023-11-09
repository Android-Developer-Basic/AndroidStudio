plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-script-runtime")
}

gradlePlugin {
    plugins {
        create("greeting-plugin") {
            id = "ru.otus.greeting"
            implementationClass = "ru.otus.GreetingPlugin"
        }
    }
}