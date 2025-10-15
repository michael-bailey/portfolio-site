package io.github.michael_bailey.spring_blog.controller.base

import io.github.michael_bailey.spring_blog.action.*
import io.ktor.http.*
import kotlinx.serialization.serializer

abstract class BaseController : IController {

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


	protected fun redirect(url: Url, bool: Boolean): IActionResult =
		RedirectActionResult(url, bool)

	protected fun redirect(urlString: String, bool: Boolean): IActionResult =
		redirect(Url(urlString), bool)

	protected fun notFoundError(): IActionResult = NotFoundResult()

}