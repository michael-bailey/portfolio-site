package io.github.michael_bailey.spring_blog.action

import io.ktor.server.routing.*

interface IActionResult {
	suspend fun executeResult(call: RoutingCall)
}