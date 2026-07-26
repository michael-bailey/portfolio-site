package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import task.RunNpmCommandTask
import task.RunNpmInstallTask


class JavascriptProject : Plugin<Project> {

	private val group = "Javascript Project"

	override fun apply(project: Project) {
		val configuration = project.extensions.create(
			"javascript",
			JavascriptProjectConfig::class.java
		)

		project.tasks.register("RunInstall", RunNpmInstallTask::class.java) { task ->
			task.group = group
			task.workingDir = project.projectDir
		}

		project.tasks.register("RunDevelopment", RunNpmCommandTask::class.java) { task ->
			task.group = group
			task.workingDir = project.projectDir

			task.npmScriptName = "dev"
		}

		project.tasks.register("RunBuild", RunNpmCommandTask::class.java) { task ->
			task.dependsOn("RunInstall")

			task.group = group
			task.workingDir = project.projectDir

			task.npmScriptName = "build"
		}
	}
}