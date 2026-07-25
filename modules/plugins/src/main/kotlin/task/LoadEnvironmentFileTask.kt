package task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.io.FileNotFoundException

abstract class LoadEnvironmentFileTask : DefaultTask() {

	private val projectDir = project.projectDir.path
	private val projectRootDir = project.rootProject.projectDir.path

	@Input
	var includeRoot = false

	@get:Internal
	internal var envMap = mapOf<String, String>()

	@TaskAction
	fun action() {
		if (includeRoot) loadEnvironmentFromFile(path = "${projectRootDir}/.env")
		loadEnvironmentFromFile(path = "${projectDir}/.env")
	}

	private fun loadEnvironmentFromFile(path: String) {
		val file = File(path)
		if (!file.exists()) throw FileNotFoundException("project .env file does not exist, but was requested to be included")

		envMap = file.readLines().fold(envMap) { acc, line ->
			val (variable, value) = line.split("=")
			acc + mapOf(variable to value)
		}
	}
}