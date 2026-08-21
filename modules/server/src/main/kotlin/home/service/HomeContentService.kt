package net.michael_bailey.home.service

import net.michael_bailey.home.model.ContentSection
import net.michael_bailey.home.repository.AboutContentRepository
import net.michael_bailey.home.repository.HobbyContentRepository
import net.michael_bailey.home.repository.ProjectContentRepository
import net.michael_bailey.home.repository.TechnologiesContentRepository
import org.koin.core.annotation.Single

@Single
class HomeContentService(
	private val aboutContent: AboutContentRepository,
	private val projectContent: ProjectContentRepository,
	private val hobbyContent: HobbyContentRepository,
	private val technologiesContent: TechnologiesContentRepository,
) {

	fun getHomeContentSections() = emptyList<ContentSection>()

//	fun getHomeContentSections() = aboutContent.getContentSections() +
//		projectContent.getContentSections() +
//		technologiesContent.getContentSections() +
//		hobbyContent.getContentSections()
}