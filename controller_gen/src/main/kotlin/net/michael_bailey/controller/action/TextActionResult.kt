package net.michael_bailey.controller.action

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class TextActionResult(
	private val content: String,
	private val statusCode: HttpStatusCode = HttpStatusCode.OK,
	private val contentType: ContentType = ContentType.Text.Plain,
): IActionResult {

	override suspend fun executeResult(call: RoutingCall) {
		call.respondText(content, status = statusCode)
	}

}