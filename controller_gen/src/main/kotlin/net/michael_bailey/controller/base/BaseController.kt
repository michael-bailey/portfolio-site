package net.michael_bailey.controller.base

import bitlib.authentication.IViewerContext
import io.ktor.http.*
import net.michael_bailey.controller.action.IActionResult
import net.michael_bailey.controller.action.NotFoundResult
import net.michael_bailey.controller.action.RedirectActionResult
import org.koin.core.scope.Scope

abstract class BaseController : IController {

	override lateinit var vc: IViewerContext
	override lateinit var scope: Scope

	protected fun redirect(url: Url, bool: Boolean): IActionResult =
		RedirectActionResult(url, bool)

	protected fun redirect(urlString: String, bool: Boolean): IActionResult =
		redirect(Url(urlString), bool)

	protected fun notFound(): IActionResult = NotFoundResult()

}