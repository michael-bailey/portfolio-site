package plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import task.IncrementVersionTask
import task.IncrementVersionTask.SemanticPosition

class VersionManagerPlugin: Plugin<Project> {

	private val group = "Version Management Plugin"

	override fun apply(target: Project) {
		target.tasks.register("incrementPatch", IncrementVersionTask::class.java) {
			it.group = group
			it.semanticPosition = SemanticPosition.Patch
		}
		target.tasks.register("incrementMinor", IncrementVersionTask::class.java) {
			it.group = group
			it.semanticPosition = SemanticPosition.Minor
		}
		target.tasks.register("incrementMajor", IncrementVersionTask::class.java) {
			it.group = group
			it.semanticPosition = SemanticPosition.Major
		}
	}
}