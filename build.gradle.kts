import org.jetbrains.kotlin.tooling.core.closure

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

open class GreetingExtension(
    var greeting: String = "Hello",
    var name: String = "buddy"
)

// Apply the plugin
apply<GreetingPlugin>()

configure<GreetingExtension> {
    greeting = "Здравствуй"
    name = "родной"
}

// Plugin
class GreetingPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val greeting = project.extensions.create<GreetingExtension>("GreetingExtension")
        project.extensions.add("greeting", greeting)
        project.task("printGreeting") {
            doLast {
                println(greeting.greeting)
            }
        }
        project.task("printName") {
            dependsOn("printGreeting")
            doLast {
                println(greeting.name)
            }
        }
    }
}
