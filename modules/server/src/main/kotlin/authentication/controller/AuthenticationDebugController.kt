package net.michael_bailey.authentication.controller

import io.ktor.server.auth.*
import io.ktor.server.html.*
import io.ktor.server.routing.*
import net.michael_bailey.authentication.model.Principal
import net.michael_bailey.home.controller.HomeController
import net.michael_bailey.kotlinx.html.ParagraphArticle
import net.michael_bailey.kotlinx.html.layout.mainHead
import net.michael_bailey.kotlinx.html.layout.mainLayout
import org.koin.core.annotation.Factory
import org.koin.ktor.ext.inject

@Factory
class AuthenticationDebugController(

) {

	suspend fun index(call: RoutingCall) {

		val principal = call.principal<Principal>()

		call.respondHtml {
			mainHead()
			mainLayout {
				basicSection {
					header = "Session Information"
					paragraphArticle {
						when (principal) {
							is Principal.Guest -> guestInformation(principal)
							is Principal.Service -> serviceInformation(principal)
							is Principal.User -> userInformation(principal)
							null -> nullInformation()
						}
					}
				}
			}
		}
	}

	private fun ParagraphArticle.guestInformation(principal: Principal.Guest) {
		header = "Viewer: Guest"
		para {
			+"Host: ${principal.host}"
		}
	}

	private fun ParagraphArticle.userInformation(principal: Principal.User) {
		header = "Viewer: User"
		para {
			+"Id: ${principal.id}"
		}
	}

	private fun ParagraphArticle.serviceInformation(principal: Principal.Service) {
		header = "Viewer: Service"
		para {
			+"Host: $principal.host"
		}
	}

	private fun ParagraphArticle.nullInformation() {
		header = "Viewer: null"
		para {
			+"""
				Congratulations on getting here, you've managed to get into an invalid state.
				Please clear your browser data for this page, and try again.
				Naughty boi...
			""".trimIndent()
		}
	}

	companion object {
		fun Routing.setupAuthenticationController() {
			authenticate {
				route("/auth") {
					this.route("/") {
						val controller: AuthenticationDebugController by inject()
						get { controller.index(call) }
					}

					this.route("/index.css") {
						val controller: HomeController by inject()
						get { controller.styles(call) }
					}
				}
			}
		}
	}
}