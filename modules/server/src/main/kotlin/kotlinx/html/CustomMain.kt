package net.michael_bailey.kotlinx.html

import kotlinx.html.HTMLTag
import kotlinx.html.TagConsumer
import kotlinx.html.emptyMap
import kotlinx.html.visit

open class CustomMain(
	initialAttributes: Map<String, String>,
	override val consumer: TagConsumer<*>
) : HTMLTag(
	tagName = "main",
	consumer = consumer,
	initialAttributes = initialAttributes,
	namespace = null,
	inlineTag = false,
	emptyTag = false
), Main {

	override fun section(header: String, block: Section.() -> Unit) {
		CustomSection(
			initialAttributes = emptyMap,
			consumer = consumer
		).visit {
			h2 { + header}
			block()
		}
	}


}