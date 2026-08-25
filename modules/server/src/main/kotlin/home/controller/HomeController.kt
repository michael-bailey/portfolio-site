package net.michael_bailey.home.controller

import io.ktor.server.html.*
import io.ktor.server.routing.*
import net.michael_bailey.extensions.respondCss
import net.michael_bailey.home.service.HomeContentService
import net.michael_bailey.kotlinx.html.layout.applyMainLayout
import net.michael_bailey.kotlinx.html.layout.mainHead
import net.michael_bailey.kotlinx.html.layout.mainLayout
import org.koin.core.annotation.Factory
import org.koin.ktor.ext.inject

@Factory
class HomeController(
	private val homeContentService: HomeContentService
) {

	suspend fun index(call: RoutingCall) {

		val sections = homeContentService.getHomeContentSections()

		call.respondHtml {
			mainHead()
			mainLayout {
				sections.forEach { section ->
					basicSection {
						section.articles.forEach { article ->
							paragraphArticle {
								header = article.header
								article.paragraphs.forEach { content ->
									para {
										+content
									}
								}
							}
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
			this.route("/") {
				val controller: HomeController by inject()
				get { controller.index(call) }
			}

			this.route("/index.css") {
				val controller: HomeController by inject()
				get { controller.styles(call) }
			}
		}
	}
}