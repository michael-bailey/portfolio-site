package task

import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Input

open class RunNpmInstallTask: Exec() {
	override fun exec() {
		this.commandLine("sh", "-c", "npm install")
		super.exec()
	}
}