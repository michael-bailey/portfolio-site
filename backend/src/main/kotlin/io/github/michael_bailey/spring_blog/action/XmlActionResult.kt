package io.github.michael_bailey.spring_blog.action


import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import nl.adaptivity.xmlutil.serialization.XML


class XmlActionResult<T>(
	private val content: T,
	private val serialiser: KSerializer<T>,
): IActionResult where T: Any {

	@OptIn(InternalSerializationApi::class)
	override suspend fun executeResult(call: RoutingCall) {

		@Suppress("UNCHECKED_CAST")
		val jsonString: String = XML.encodeToString(
			serialiser, this.content
		)
		call.respondText(jsonString, ContentType.Application.Xml)
	}

}