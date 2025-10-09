package io.github.michael_bailey.spring_blog.controller.base

import io.github.michael_bailey.spring_blog.security.viewer.ViewerContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

/**
 * Base interface for all controllers.
 *
 * @property[vc] The viewer context for the current request.
 * @property[preExec] Action to run before the controller action is executed.
 * @property[postExec] Action to run after the controller action is executed.
 */
interface IController {
	var vc: ViewerContext

	val logger: Logger get() = getLogger(this::class.java)

	fun preExec()
	fun postExec()

}