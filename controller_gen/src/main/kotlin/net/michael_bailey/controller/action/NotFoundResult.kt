package net.michael_bailey.controller.action

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class NotFoundResult(

): IActionResult {
	override suspend fun executeResult(call: RoutingCall) {
		call.respond(HttpStatusCode.NotFound, "Resource not found.")
	}
}