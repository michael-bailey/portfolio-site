package net.michael_bailey.kotlinx.html

interface ArticleContainer {
	fun paragraphArticle(block: ParagraphArticle.() -> Unit)
}