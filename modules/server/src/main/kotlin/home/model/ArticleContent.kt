package net.michael_bailey.home.model

sealed interface ArticleContent {
	data class Paragraph(
		val text: String,
	) : ArticleContent

	data class Image(
		val href: String,
		val alt: String,
	) : ArticleContent
}