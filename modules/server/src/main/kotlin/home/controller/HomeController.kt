package net.michael_bailey.home.controller

import io.ktor.server.html.*
import io.ktor.server.routing.*
import kotlinx.html.p
import net.michael_bailey.extensions.respondCss
import net.michael_bailey.home.model.ContentSection
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

//		val sections = homeContentService.getHomeContentSections()
		val sections = emptyList<ContentSection>()

		call.respondHtml {
			mainHead()
			mainLayout {
				sections.forEach { section ->
					section(section.header) {
						section.articles.forEach { article ->
							paragraphArticle(article.header) {
								article.paragraphs.forEach { para ->
									p {
										+para
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