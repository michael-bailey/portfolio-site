package net.michael_bailey.components

import kotlinx.html.HTMLTag
import kotlinx.html.TagConsumer
import kotlinx.html.emptyMap
import kotlinx.html.visit

abstract class HtmlComponent(
	tagName: String,
	consumer: TagConsumer<*>,
	initialAttributes: Map<String, String> = emptyMap,
	namespace: String? = null,
	inlineTag: Boolean = false,
	emptyTag: Boolean = false
): HTMLTag(
	tagName = tagName,
	consumer = consumer,
	initialAttributes = initialAttributes,
	namespace = namespace,
	inlineTag = inlineTag,
	emptyTag = emptyTag
) {

	fun render() = visit {
		renderContents()
	}

	abstract fun renderContents()

}