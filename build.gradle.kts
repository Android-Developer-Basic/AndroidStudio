// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    id("ru.otus.greeting")
}

tasks.register("printSomething") {
    doLast {
        println("Hello World!")
    }
}

greeting {
    greeting = "Здравствуй"
    name = "родной"
}
