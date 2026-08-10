package net.michael_bailey.observability

import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import org.koin.ktor.ext.inject

fun Routing.setupHealthController() {

	val registry: PrometheusMeterRegistry by inject()

	get("/metrics-micrometer") {
		call.respond(registry.scrape())
	}
}