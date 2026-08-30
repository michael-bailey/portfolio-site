package net.michael_bailey.home.model

data class ContentSection(
	val header: String,
	val description: String,
	val articles: List<Article>,
)
