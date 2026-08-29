package net.michael_bailey.home.model

data class MultiMediaArticle(
	override val header: String,
	val paragraphs: List<ArticleContent>,
) : Article