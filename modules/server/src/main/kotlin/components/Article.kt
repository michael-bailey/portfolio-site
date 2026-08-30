package net.michael_bailey.components

import kotlinx.html.*
import net.michael_bailey.kotlinx.html.ArticleContent

class Article(consumer: TagConsumer<*>) : HtmlComponent(
	tagName = "article", consumer
), HtmlBlockTag, FlowOrPhrasingContent, ArticleContent {

	override var header: String = ""

	private val components = mutableListOf<Article.() -> Unit>()

	override fun para(block: P.() -> Unit) {
		components += {
			P(
				initialAttributes = emptyMap,
				consumer = consumer
			).block()
		}
	}

	override fun customContent(block: HtmlBlockTag.() -> Unit) {
		components += {
			block()
		}
	}

	override fun renderContents() {
		h3 {
			+this@Article.header
		}
		components.forEach {
			it()
		}
	}
}