package net.michael_bailey.application.plugins

import io.ktor.server.application.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import org.koin.ktor.ext.inject

fun Application.setupMetrics() {
	install(MicrometerMetrics) {
		val registry by this@setupMetrics.inject<MeterRegistry>()
		this.registry = registry
	}

	routing {
		get("/metrics") {
			val registry by inject<PrometheusMeterRegistry>()
			call.respond(registry.scrape())
		}
	}
}