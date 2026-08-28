package net.michael_bailey.home.repository

import net.michael_bailey.home.model.ContentArticle
import net.michael_bailey.home.model.ContentSection
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

	private fun getScoutingArticle(): ContentArticle = ContentArticle(
		header = "Scouting",
		paragraphs = listOf(
			"""
				I've been a part of my local scout group, since beavers.
			""".trimIndent()
		)
	)

	private fun getNavigationArticle(): ContentArticle = ContentArticle(
		header = "Navigation",
		paragraphs = listOf(
			"""
				As part of scouting, I've learnt basic map reading and compass skills.
				From this i gained an interest in more complex navigational skills and tools.
			""".trimIndent()
		)
	)

	private fun getCampingArticle(): ContentArticle = ContentArticle(
		header = "Camping",
		paragraphs = listOf(
			"""
				Camping has been a bit of a mix for me. Whilst i enjoy it, I have hay fever
				which can be a limit to how much i can do and focus on.
			""".trimIndent()
		)
	)
}