package net.michael_bailey.kotlinx.html

import kotlinx.html.HtmlBlockTag
import kotlinx.html.P

interface ArticleContent {

	var header: String

	fun para(block: P.() -> Unit)
	fun customContent(block: HtmlBlockTag.() -> Unit)
}