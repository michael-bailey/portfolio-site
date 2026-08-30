package net.michael_bailey.application.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callid.*
import kotlin.uuid.Uuid

fun Application.setupCallId() {
	install(CallId) {
		header(HttpHeaders.XRequestId)
		verify { callId: String ->
			callId.isNotEmpty()
		}
		this.generate {
			Uuid.generateV7().toString()
		}
	}
}