package net.michael_bailey.controller.base

import kotlinx.serialization.serializer
import net.michael_bailey.controller.action.IActionResult
import net.michael_bailey.controller.action.JsonActionResult
import net.michael_bailey.controller.action.TextActionResult
import net.michael_bailey.controller.action.XmlActionResult


abstract class RestController : BaseController() {
	/**
	 * Returns a text response with the given content.
	 */
	protected fun text(content: String): IActionResult = TextActionResult(content)

	protected inline fun <reified T : Any> json(content: T): IActionResult =
		JsonActionResult(
			content, serialiser = serializer()
		)

	protected inline fun <reified T : Any> xml(content: T): IActionResult =
		XmlActionResult(
			content, serialiser = serializer()
		)
}