package net.michael_bailey.components

import kotlinx.html.*

class Navigation(
	consumer: TagConsumer<*>
): HTMLTag(
	tagName = "nav",
	consumer = consumer,
	initialAttributes = emptyMap,
	namespace = null,
	inlineTag = false,
	emptyTag = false
), NavigationContainer, HtmlBlockTag, FlowOrPhrasingContent {

	private val components = mutableListOf<Navigation.() -> Unit>()

	override fun addLink() {
		TODO("Not yet implemented")
	}

	override fun addDropdown() {
		TODO("Not yet implemented")
	}

	fun render() = visit {
		components.forEach {
			it()
		}
	}
}