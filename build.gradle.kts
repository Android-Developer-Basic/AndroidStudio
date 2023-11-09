// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}

tasks.register("printSomething") {
    doLast {
        println("Hello World!")
    }
}

// Plugin
class GreetingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("hello") {
            doLast {
                println("Hello")
            }
        }
        project.tasks.register("printHelloWorld") {
            dependsOn("hello")
            doLast {
                println("World")
            }
        }
    }
}

// Apply the plugin
apply<GreetingPlugin>()