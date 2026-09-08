package net.michael_bailey.authentication

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import net.michael_bailey.authentication.model.Principal
import net.michael_bailey.authentication.services.SessionService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.ktor.ext.inject

@Module
@Configuration
@ComponentScan("net.michael_bailey.authentication")
object AuthenticationModule {

	fun Application.setupAuthentication() {

		val sessionService: SessionService by inject()

		install(Sessions) {
			cookie<Principal>("Viewer", sessionService)
		}

		install(Authentication) {
			configureSession()
		}
	}

	private  fun AuthenticationConfig.configureSession() {
		session<Principal> {
			challenge {
				call.sessions.set<Principal>(
					Principal.Guest(
						host = this.call.request.origin.remoteAddress
					))
				call.respondRedirect { }
			}

			validate {
				it
			}
		}
	}
}