package net.michael_bailey.kotlinx.html

import kotlinx.html.*

class ParagraphArticle(
	initialAttributes: Map<String, String>,
	override val consumer: TagConsumer<*>
): HTMLTag("article", consumer, initialAttributes, null, false, false), ArticleContent, HtmlBlockTag, FlowOrPhrasingContent {

	private val components: MutableList<ParagraphArticle.() -> Unit> = mutableListOf()

	override var header: String = ""

	override fun para(block: P.() -> Unit) {
		components += {
			p(block = block)
		}
	}

	override fun customContent(block: HtmlBlockTag.() -> Unit) {
		components += block
	}

	fun render() {
		visit {
			h3 {
				+this@visit.header
			}
			components.forEach {
				it()
			}
		}
	}
}