package net.michael_bailey.controller.base

import bitlib.authentication.IViewerContext
import org.koin.core.scope.Scope

/**
 * Base interface for all controllers.
 *
 * @property[vc] The viewer context for the current request.
 * @property[preExec] Action to run before the controller action is executed.
 * @property[postExec] Action to run after the controller action is executed.
 */
interface IController {

	var vc: IViewerContext
	var scope: Scope

	fun preExec()
	fun postExec()
}