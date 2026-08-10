package net.michael_bailey.kotlinx.html

import kotlinx.html.HTMLTag
import kotlinx.html.TagConsumer

class CustomArticle(
	initialAttributes: Map<String, String>,
	override val consumer: TagConsumer<*>
): HTMLTag("article", consumer, initialAttributes, null, false, false), Article {

}