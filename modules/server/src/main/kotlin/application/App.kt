package net.michael_bailey.application

import io.ktor.server.application.*
import io.ktor.server.routing.*
import net.michael_bailey.application.plugins.setupCallId
import net.michael_bailey.application.plugins.setupMetrics
import net.michael_bailey.home.controller.HomeController.Companion.setupHome
import net.michael_bailey.observability.setupHealthController
import org.koin.core.annotation.KoinApplication
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.koin.plugin.module.dsl.withConfiguration

@KoinApplication(modules = [
	AppModule::class
])
object App {
	fun Application.setup() {

		install(Koin) {
			slf4jLogger(org.koin.core.logger.Level.WARNING)
			withConfiguration<App>()
		}

		setupCallId()
		setupMetrics()

		routing {
			setupHealthController()
			setupHome()
		}
	}
}