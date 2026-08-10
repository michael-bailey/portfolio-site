package net.michael_bailey.kotlinx.html

import kotlinx.html.*

class CustomSection(
	initialAttributes: Map<String, String>,
	override val consumer: TagConsumer<*>
): HTMLTag("section", consumer, initialAttributes, null, false, false), Section {

	fun h1(block: H1.() -> Unit) {
		H1(emptyMap, consumer).visit(block)
	}

	override fun paragraphArticle(header: String, block: Article.() -> Unit) {
		CustomArticle(emptyMap, consumer).visit {
			h2 {
				+header
			}
			block()
		}
	}
}