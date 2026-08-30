package net.michael_bailey.components

import kotlinx.html.TagConsumer
import kotlinx.html.img
import net.michael_bailey.net.michael_bailey.util.HtmlComponentTest
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class ArticleTest : HtmlComponentTest<Article>() {
	override fun newInstance(consumer: TagConsumer<*>): Article =
		Article(consumer)

	@Test
	fun `Rendering does not cause concurrent modification error`() {
		build {
			para {
				+"Hello world"
			}
			customContent {
				img(alt = "", src = "some source")
			}
		}

		assertTrue { final.childNodes.length == 1 }
	}
}