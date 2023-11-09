package ru.otus

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

open class GreetingExtension(
    var greeting: String = "Hello",
    var name: String = "buddy"
)

// Plugin
open class GreetingPlugin : Plugin<Project> {
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
