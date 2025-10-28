package net.michael_bailey.controller.action

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class RedirectActionResult(
	private val url: Url = Url("/"),
	private val permanent: Boolean = false,
): IActionResult {
	override suspend fun executeResult(call: RoutingCall) {
		call.respondRedirect(url, permanent)
	}
}