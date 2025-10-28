package io.github.michael_bailey.spring_blog.plugin

import io.github.michael_bailey.spring_blog.scope.RequestScope
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.util.*

val requestScopeKey = AttributeKey<RequestScope>("RequestScope")

val RequestScopePlugin = createApplicationPlugin(
	name = "RequestScopePlugin",
) {
	onCall { call ->

		val requestScope: RequestScope = RequestScope()

		call.attributes[requestScopeKey] = requestScope

		call.application.environment.log.info("Request received for path: ${call.request.path()}")
	}
	onCallRespond { call ->
		call.application.environment.log.info("Response sent for path: ${call.request.path()}")
	}
}