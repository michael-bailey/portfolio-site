package net.michael_bailey.home.service

import io.micrometer.core.instrument.MeterRegistry
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
	private val meters: MeterRegistry
) {

	fun getHomeContentSections(): List<ContentSection> {

		meters.counter(SECTION_COUNTER_NAME).increment()

		return aboutContent.getContentSections() +
			projectContent.getContentSections() +
			technologiesContent.getContentSections() +
			hobbyContent.getContentSections()
	}

	companion object {
		private const val SECTION_COUNTER_NAME = "home.content.service"
	}
}