package net.michael_bailey.home.repository

import net.michael_bailey.home.model.Article
import net.michael_bailey.home.model.ArticleContent
import net.michael_bailey.home.model.ContentSection
import net.michael_bailey.home.model.MultiMediaArticle
import org.koin.core.annotation.Single

@Single
class HobbyContentRepository {
	fun getContentSections(): List<ContentSection> = listOf(
		ContentSection(
			header = "Hobbies",
			description = "This is work in progress, expect stuff to do with swimming, electronics, gym, and robotics\"\"\".trimIndent()",
			articles = listOf(
				getScoutingArticle(),
				getCampingArticle(),
				getNavigationArticle(),
			)
		),
	)

	private fun getScoutingArticle(): Article = MultiMediaArticle(
		header = "Scouting", content = listOf(
			ArticleContent.Paragraph(
				"""
				I've been a part of my local scout group, since beavers.
			""".trimIndent()
			)
		)
	)


	private fun getNavigationArticle(): Article = MultiMediaArticle(
		header = "Navigation", content = listOf(
			ArticleContent.Paragraph(
				"""
				As part of scouting, I've learnt basic map reading and compass skills.
				From this i gained an interest in more complex navigational skills and tools.
			""".trimIndent()
			)
		)
	)

	private fun getCampingArticle(): Article = MultiMediaArticle(
		header = "Camping", content = listOf(
			ArticleContent.Paragraph(
				"""
				Camping has been a bit of a mix for me. Whilst i enjoy it, I have hay fever
				which can be a limit to how much i can do and focus on.
			""".trimIndent()
			)
		)
	)
}