package task

import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Input

open class RunNpmCommandTask: Exec() {

	@get:Input
	var npmScriptName: String = "start"

	override fun exec() {
		this.commandLine("sh", "-c", "npm run $npmScriptName")

		super.exec()
	}

}