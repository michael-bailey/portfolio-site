package net.michael_bailey.controller.action


import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json


class JsonActionResult<T>(
	private val content: T,
	private val serialiser: KSerializer<T>,
) : IActionResult where T : Any {

	@OptIn(InternalSerializationApi::class)
	override suspend fun executeResult(call: RoutingCall) {

		val jsonString: String = Json.encodeToString(
			serialiser, this.content
		)
		call.respondText(jsonString, ContentType.Application.Json)
	}

}