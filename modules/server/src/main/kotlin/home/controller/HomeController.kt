package net.michael_bailey.home.controller

import io.ktor.server.html.*
import io.ktor.server.routing.*
import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.Tags
import kotlinx.html.p
import net.michael_bailey.extensions.respondCss
import net.michael_bailey.home.model.Article
import net.michael_bailey.home.model.ArticleContent
import net.michael_bailey.home.model.MultiMediaArticle
import net.michael_bailey.home.model.ParagraphArticle
import net.michael_bailey.home.service.HomeContentService
import net.michael_bailey.kotlinx.html.SectionContent
import net.michael_bailey.kotlinx.html.layout.applyMainLayout
import net.michael_bailey.kotlinx.html.layout.mainHead
import net.michael_bailey.kotlinx.html.layout.mainLayout
import org.koin.core.annotation.Factory
import org.koin.ktor.ext.inject
import net.michael_bailey.kotlinx.html.ArticleContent as ArticleContentHtml

@Factory
class HomeController(
	private val homeContentService: HomeContentService,
	private val meterRegistry: MeterRegistry
) {

	suspend fun index(call: RoutingCall) {

		val requestCounter = createRemoteHostCounter(call)
		requestCounter.increment()

		val sections = homeContentService.getHomeContentSections()

		call.respondHtml {
			mainHead()
			mainLayout {
				sections.forEach { section ->
					basicSection {
						header = section.header
						section.articles.forEach { article -> handleArticle(article) }
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

	private fun SectionContent.handleArticle(article: Article) {
		when (article) {
			is MultiMediaArticle -> handleMultiMediaArticle(article)
			is ParagraphArticle -> handleParagraphArticle(article)
		}
	}

	private fun SectionContent.handleParagraphArticle(article: ParagraphArticle) {
		paragraphArticle {
			header = article.header
			article.paragraphs.forEach { content ->
				para {
					+content
				}
			}
		}
	}

	private fun SectionContent.handleMultiMediaArticle(article: MultiMediaArticle) {
		multiMediaArticle {
			header = article.header
			article.content.forEach { content ->
				when (content) {
					is ArticleContent.Image -> handleImage(content)
					is ArticleContent.Paragraph -> handleParagraph(content)
				}
			}
		}
	}

	private fun ArticleContentHtml.handleImage(content: ArticleContent.Image) {
		customContent {

		}
	}

	private fun ArticleContentHtml.handleParagraph(content: ArticleContent.Paragraph) {
		customContent {
			this.p {
				+content.text
			}
		}
	}

	private fun createRemoteHostCounter(call: RoutingCall): Counter {
		val tags = Tags.of(HOST_TAG_NAME, call.request.local.remoteHost)
		val requestCounter = meterRegistry
			.counter(HOME_REQUEST_KEY, tags)
		return requestCounter
	}

	companion object {

		private const val HOME_REQUEST_KEY = "home.request.counter"
		private const val HOST_TAG_NAME = "host"

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