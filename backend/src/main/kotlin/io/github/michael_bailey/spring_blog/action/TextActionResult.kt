package io.github.michael_bailey.spring_blog.action

import io.ktor.server.response.*
import io.ktor.server.routing.*

class TextActionResult(private val content: String): IActionResult {

	override suspend fun executeResult(call: RoutingCall) {
		call.respondText(content)
	}

}