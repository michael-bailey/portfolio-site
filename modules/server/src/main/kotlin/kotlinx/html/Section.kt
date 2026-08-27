package net.michael_bailey.kotlinx.html

import kotlinx.html.*
import net.michael_bailey.components.HtmlComponent

class Section(
	initialAttributes: Map<String, String>,
	override val consumer: TagConsumer<*>,
) : HtmlComponent(
	tagName = "section", consumer, initialAttributes, null, false, false
), SectionContent, HtmlBlockTag, FlowOrPhrasingContent {

	private var components = mutableListOf<Section.() -> Unit>()

	override var header: String = ""

	override fun paragraphArticle(block: ParagraphArticle.() -> Unit) {
		components += {
			ParagraphArticle(
				initialAttributes = emptyMap, consumer = consumer
			).apply(block).render()
		}
	}

	override fun renderContents() {
		h2 {
			+this@Section.header
		}
		components.forEach {
			it()
		}
	}
}