// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

tasks.register("printSomething") {
    doLast {
        println("Hello World!")
    }
}

// Plugin
class GreetingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.task("hello") {
            doLast {
                println("Hello")
            }
        }
        project.task("printHelloWorld") {
            dependsOn("hello")
            doLast {
                println("World")
            }
        }
    }
}

// Apply the plugin
apply<GreetingPlugin>()