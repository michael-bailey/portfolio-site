package net.michael_bailey.application

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.metrics.micrometer.*
import io.ktor.server.plugins.callid.*
import io.ktor.server.routing.*
import net.michael_bailey.home.controller.HomeController.Companion.setupHome
import net.michael_bailey.observability.setupHealthController
import org.koin.core.annotation.KoinApplication
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.koin.plugin.module.dsl.withConfiguration
import kotlin.uuid.Uuid

@KoinApplication(modules = [
	AppModule::class
])
object App {
	fun Application.setup() {

		install(Koin) {
			slf4jLogger(org.koin.core.logger.Level.WARNING)
			withConfiguration<App>()
		}

		install(MicrometerMetrics) {
			this.registry = registry
		}

		install(CallId) {
			header(HttpHeaders.XRequestId)
			verify { callId: String ->
				callId.isNotEmpty()
			}
			this.generate {
				Uuid.generateV7().toString()
			}
		}

		routing {
			setupHealthController()
			setupHome()
		}
	}
}