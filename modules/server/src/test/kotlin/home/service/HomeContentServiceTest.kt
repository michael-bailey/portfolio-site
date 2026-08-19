package net.michael_bailey.home.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import net.michael_bailey.home.model.ContentSection
import net.michael_bailey.home.repository.AboutContentRepository
import net.michael_bailey.home.repository.HobbyContentRepository
import net.michael_bailey.home.repository.ProjectContentRepository
import net.michael_bailey.home.repository.TechnologiesContentRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HomeContentServiceTest {

	private lateinit var aboutContent: AboutContentRepository
	private lateinit var projectContent: ProjectContentRepository
	private lateinit var hobbyContent: HobbyContentRepository
	private lateinit var technologiesContent: TechnologiesContentRepository
	private lateinit var service: HomeContentService

	@BeforeEach
	fun setUp() {
		aboutContent = mockk()
		projectContent = mockk()
		hobbyContent = mockk()
		technologiesContent = mockk()

		service = HomeContentService(
			aboutContent = aboutContent,
			projectContent = projectContent,
			hobbyContent = hobbyContent,
			technologiesContent = technologiesContent,
		)
	}

	@Test
	fun `getHomeContentSections concatenates sections from all repositories in order`() {
		val aboutSections = listOf(ContentSection(
			"about-1",
			description = "TODO()",
			articles = emptyList()
		))
		val projectSections =
			listOf(ContentSection(
				"project-1",
				description = "",
				articles = emptyList()
			), ContentSection(
				"project-2",
				description = "",
				articles = emptyList()
			))
		val technologiesSections = listOf(ContentSection(
			"tech-1",
			description = "",
			articles = emptyList()
		))
		val hobbySections = listOf(ContentSection(
			"hobby-1",
			description = "",
			articles = emptyList()
		))

		every { aboutContent.getContentSections() } returns aboutSections
		every { projectContent.getContentSections() } returns projectSections
		every { technologiesContent.getContentSections() } returns technologiesSections
		every { hobbyContent.getContentSections() } returns hobbySections

		val result = service.getHomeContentSections()

		// Order matters: about, project, technologies, hobby
		assertEquals(
			aboutSections + projectSections + technologiesSections + hobbySections,
			result
		)

		verify(exactly = 1) { aboutContent.getContentSections() }
		verify(exactly = 1) { projectContent.getContentSections() }
		verify(exactly = 1) { technologiesContent.getContentSections() }
		verify(exactly = 1) { hobbyContent.getContentSections() }
	}

	@Test
	fun `getHomeContentSections returns empty list when all repositories return empty lists`() {
		every { aboutContent.getContentSections() } returns emptyList()
		every { projectContent.getContentSections() } returns emptyList()
		every { technologiesContent.getContentSections() } returns emptyList()
		every { hobbyContent.getContentSections() } returns emptyList()

		val result = service.getHomeContentSections()

		assertEquals(emptyList<ContentSection>(), result)
	}
}