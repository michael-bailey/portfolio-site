package net.michael_bailey.application

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callid.*
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import kotlin.uuid.Uuid

@Module
@Configuration
@ComponentScan("net.michael_bailey.application")
object AppModule {
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
}