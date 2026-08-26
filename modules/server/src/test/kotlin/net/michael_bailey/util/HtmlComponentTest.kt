package net.michael_bailey.net.michael_bailey.util

import kotlinx.html.TagConsumer
import kotlinx.html.dom.createHTMLDocument
import net.michael_bailey.components.HtmlComponent
import org.w3c.dom.Document

abstract class HtmlComponentTest<T: HtmlComponent> {

	val document = createHTMLDocument()

	val final: Document by lazy {
		document.finalize()
	}

	abstract fun newInstance(consumer: TagConsumer<*>): T

	inline fun build(block: T.() -> Unit) {
		document.apply {
			newInstance(this).apply(block).render()
		}
	}
}
