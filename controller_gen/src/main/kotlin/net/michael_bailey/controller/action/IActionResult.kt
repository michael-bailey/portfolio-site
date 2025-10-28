package net.michael_bailey.controller.action

import io.ktor.server.routing.*

interface IActionResult {
	suspend fun executeResult(call: RoutingCall)
}