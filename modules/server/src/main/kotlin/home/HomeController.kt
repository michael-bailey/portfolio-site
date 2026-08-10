package net.michael_bailey.home

import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.HTML
import kotlinx.html.p
import net.michael_bailey.extensions.respondCss
import net.michael_bailey.kotlinx.html.layout.applyMainLayout
import net.michael_bailey.kotlinx.html.layout.mainHead
import net.michael_bailey.kotlinx.html.layout.mainLayout
import org.koin.core.annotation.Single
import org.koin.ktor.ext.inject

@Single
class HomeController {

	suspend fun index(call: RoutingCall) {
		call.respondHtml {
			output(this)
		}
	}

	fun output(receiver: HTML) {
		receiver.mainHead()
		receiver.mainLayout {
			section("This is a work in progress") {
				paragraphArticle("Migration from Spring to Ktor") {
					p {
						+ buildString {
							append("My web server is currently in the process of migrating from Spring boot ")
							append("to a Ktor based web server. This is because of the ease and more extensible, ")
							append("and exposed the inner workings of Ktor are, allowing me to better control how everything works, ")
							append("without guessing what Spring will do")
						}
					}
				}
			}
		}
	}

	suspend fun styles(call: RoutingCall) {
		call.respondCss {
			applyMainLayout()
		}
	}


	companion object {
		fun Routing.setupHome() {
			val controller: HomeController by inject()
			this.route("/") {
				get { controller.index(call) }
			}

			this.route("/index.css") {
				get { controller.styles(call) }
			}
		}
	}
}