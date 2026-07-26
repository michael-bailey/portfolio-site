package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.JavaExec
import task.LoadEnvironmentFileTask

class EnvironmentFilePlugin : Plugin<Project> {

	private val group = "Environment Plugin"

	override fun apply(project: Project) {

		val extension = project.extensions.create(
			"envFile",
			EnvironmentFilePluginExtension::class.java
		)

		val loadEnvVars = project.tasks.register(
			"LoadEnvFile",
			LoadEnvironmentFileTask::class.java
		) {

			it.group = group
			it.description = """
				Loads environment variables from .env files, ready to be consumed by other executable tasks
			""".trimIndent()

			it.includeRoot = extension.includeRootEnvFile
		}

		project.tasks
			.filter { it is JavaExec || it is Exec }
			.map { it as JavaExec }
			.map { task ->

			task.dependsOn(loadEnvVars)

			task.doFirst {
				val envVars: Map<String, String> = loadEnvVars.get().envMap
				envVars.entries.forEach {
					task.environment[it.key] = it.value
				}
			}
		}
	}
}