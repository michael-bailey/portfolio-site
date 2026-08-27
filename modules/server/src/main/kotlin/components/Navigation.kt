package net.michael_bailey.components

import kotlinx.html.FlowOrPhrasingContent
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.a

class Navigation(
	consumer: TagConsumer<*>
): HtmlComponent(
	tagName = "nav",
	consumer = consumer,
), NavigationContainer, HtmlBlockTag, FlowOrPhrasingContent {

	private val components = mutableListOf<Navigation.() -> Unit>()

	override fun addLink(href: String, text: String) {
		components += {
			a(href = href) {
				+text
			}
		}
	}

	override fun addDropdown() {
		TODO("Not yet implemented")
	}

	override fun renderContents() {
		components.forEach {
			it()
		}
	}
}