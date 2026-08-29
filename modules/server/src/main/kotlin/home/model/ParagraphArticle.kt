package net.michael_bailey.home.model

data class ParagraphArticle(
	override val header: String,
	val paragraphs: List<String>,
) : Article
