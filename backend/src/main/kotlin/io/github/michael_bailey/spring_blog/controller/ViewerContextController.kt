package io.github.michael_bailey.spring_blog.controller

import net.michael_bailey.controller.action.IActionResult
import net.michael_bailey.controller.annotations.Controller
import net.michael_bailey.controller.annotations.GetRoute
import net.michael_bailey.controller.base.RestController

@Controller(basePath = "/api/viewer")
class ViewerContextController: RestController() {

	@GetRoute("/username")
	fun username(): IActionResult {
		return text(vc.name)
	}

	override fun preExec() {
		TODO("Not yet implemented")
	}

	override fun postExec() {
		TODO("Not yet implemented")
	}
}