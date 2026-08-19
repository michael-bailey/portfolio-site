package net.michael_bailey.home.repository

import net.michael_bailey.home.model.ContentSection
import org.koin.core.annotation.Single

@Single
class HobbyContentRepository {
	fun getContentSections(): List<ContentSection> = listOf(
		ContentSection(
			header = "Hobbies",
			description = "This is work in progress, expect stuff to do with swimming, electronics, gym, and robotics\"\"\".trimIndent()",
			articles = listOf()
		),
	)
}