package net.michael_bailey.kotlinx.html

interface ArticleContainer {



	fun paragraphArticle(header: String, block: ArticleContent.() -> Unit)
}