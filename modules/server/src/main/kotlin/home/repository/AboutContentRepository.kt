package net.michael_bailey.home.repository

import net.michael_bailey.home.model.ContentSection
import net.michael_bailey.home.model.ParagraphArticle
import org.koin.core.annotation.Single

@Single
class AboutContentRepository {
	fun getContentSections(): List<ContentSection> = listOf(
		ContentSection(
			header = "About",
			description = "",
			articles = listOf(
				ParagraphArticle(
					header = "Hello world",
					paragraphs = listOf(
						"""Hi there, I'm Michael. I'm a software engineer with a
							deep interest in user-focused, technology and engineering.
							I enjoy building tools to assist myself, and others, with my other
							hobbies and interests.""".trimIndent(),
						"""This site is a place for me to share projects I'm working on, write
							about technologies I 'm learning, and put into practice my
							learnings .""".trimIndent()
					)
				)
			)
		),
	)
}