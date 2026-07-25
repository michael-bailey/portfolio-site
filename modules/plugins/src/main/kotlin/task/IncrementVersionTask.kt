package task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.UntrackedTask
import java.io.File
import java.util.*

@UntrackedTask(because = "Task mutates its own state file on every run; it should never be skipped as up-to-date")
open class IncrementVersionTask: DefaultTask() {

	@Input
	var semanticPosition: SemanticPosition = SemanticPosition.Patch

	@get:Internal
	val propertiesFile: File = project.file("gradle.properties")

	@TaskAction
	fun action() {
		val props = Properties()
		if (propertiesFile.exists()) {
			propertiesFile.inputStream().use { props.load(it) }
		}

		val currentValue = props.getProperty(PROJECT_VERSION_KEY)
		val newVersion = currentValue?.let(::getNewVersion) ?: "0.0.1"

		props.setProperty(PROJECT_VERSION_KEY, newVersion)
		propertiesFile.outputStream()
			.use { props.store(it, "Updated by IncrementVersionTask") }

		project.version = newVersion
		logger.lifecycle("Version updated: ${currentValue ?: "(none)"} -> $newVersion")
	}

	private fun getNewVersion(oldVersion: String): String {
		val (major, minor, patch) = oldVersion.split(".").map { it.toInt() }
		return when (semanticPosition) {
			SemanticPosition.Major -> "${major + 1}.0.0"
			SemanticPosition.Minor -> "$major.${minor + 1}.0"
			SemanticPosition.Patch -> "$major.$minor.${patch + 1}"
		}
	}

	enum class SemanticPosition {
		Major,
		Minor,
		Patch,
	}

	companion object {
		const val PROJECT_VERSION_KEY = "projectVersion"
	}
}