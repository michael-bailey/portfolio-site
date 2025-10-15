package io.github.michael_bailey.spring_blog.controller.base

import io.github.michael_bailey.spring_blog.security.viewer.ViewerContext

abstract class RestController: BaseController() {

	// todo: ensure this is initialised
	override lateinit var vc: ViewerContext

	override fun preExec() {
		logger.info("Executing REST Controller.")
	}

	override fun postExec() {
		logger.info("Finished executing REST Controller.")
	}

}