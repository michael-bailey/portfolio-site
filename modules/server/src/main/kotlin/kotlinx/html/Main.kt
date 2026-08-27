package net.michael_bailey.kotlinx.html

import kotlinx.html.*

open class Main(
	initialAttributes: Map<String, String>,
	override val consumer: TagConsumer<*>
) : HTMLTag(
	tagName = "main",
	consumer = consumer,
	initialAttributes = initialAttributes,
	namespace = null,
	inlineTag = false,
	emptyTag = false
), SectionContainer, HtmlBlockTag, FlowOrPhrasingContent {

	var components = mutableListOf<Main.() -> Unit>()

	override fun basicSection(block: SectionContent.() -> Unit) {
		components += {
			Section(
				initialAttributes = emptyMap,
				consumer = consumer
			).apply(block).render()
		}
	}

	fun render() = visit {
		components.forEach {
			it()
		}
	}


}