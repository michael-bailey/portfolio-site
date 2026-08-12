package net.michael_bailey.kotlinx.html

interface Section {
	fun paragraphArticle(header: String, block: Article.() -> Unit)
}