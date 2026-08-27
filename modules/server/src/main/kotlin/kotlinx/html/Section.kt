package net.michael_bailey.kotlinx.html

import kotlinx.html.*

class Section(
	initialAttributes: Map<String, String>,
	override val consumer: TagConsumer<*>
): HTMLTag("section", consumer, initialAttributes, null, false, false),
	SectionContent, HtmlBlockTag, FlowOrPhrasingContent {

	private var components = mutableListOf<Section.() -> Unit>()

	override var header: String = ""

	override fun paragraphArticle(block: ParagraphArticle.() -> Unit) {
		components += {
			ParagraphArticle(
				initialAttributes = emptyMap,
				consumer = consumer
			).apply(block).render()
		}
	}


	fun render() = visit {
		h2 {
			+this@Section.header
		}
		components.forEach {
			it()
		}
	}
}