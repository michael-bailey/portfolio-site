package net.michael_bailey.observability

import io.ktor.server.application.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.prometheusmetrics.PrometheusConfig
import io.micrometer.prometheusmetrics.PrometheusMeterRegistry
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.ktor.ext.inject

@Module
@Configuration
@ComponentScan("net.michael_bailey.observability")
object ObservabilityModule {

	@Single(binds = [MeterRegistry::class])
	fun micrometerMetrics(): PrometheusMeterRegistry =
		PrometheusMeterRegistry(PrometheusConfig.DEFAULT)

	fun Application.setupObservability() {
		install(MicrometerMetrics) {
			val registry by this@setupObservability.inject<MeterRegistry>()
			this.registry = registry
		}

		routing {
			get("/metrics") {
				val registry by inject<PrometheusMeterRegistry>()
				call.respond(registry.scrape())
			}
		}
	}

}