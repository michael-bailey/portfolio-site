package net.michael_bailey.components

import kotlinx.html.TagConsumer
import net.michael_bailey.net.michael_bailey.util.HtmlComponentTest
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class NavigationTest: HtmlComponentTest<Navigation>() {

	override fun newInstance(consumer: TagConsumer<*>): Navigation =
		Navigation(consumer)

	@Test
	fun `No apply renders empty nav block`() {

		build {  }

		assertTrue { final.childNodes.length == 1 }
	}

	@Test
	fun `Adding two links renders nav block with two links`() {

		build {
			addLink(href = "/", text = "Home")
			addLink(href = "/about", text = "about")
		}

		assertTrue { final.childNodes.length == 1 }
		assertTrue { final.childNodes.item(0).childNodes.length == 2 }
		assertTrue { final.childNodes.item(0).childNodes.item(0).attributes.length == 1 }
	}
}