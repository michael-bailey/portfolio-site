package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.controller.old.RootController
import io.github.michael_bailey.spring_blog.filter.AnalyticsFilter
import io.github.michael_bailey.spring_blog.filter.ViewerContextFilter
import io.github.michael_bailey.spring_blog.model.BlogPostModel
import io.github.michael_bailey.spring_blog.service.BlogService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(
	controllers = [RootController::class],
	excludeAutoConfiguration = [SecurityAutoConfiguration::class],
	excludeFilters = [
		ComponentScan.Filter(
			type = FilterType.ASSIGNABLE_TYPE,
			classes = [
				ViewerContextFilter::class,
				AnalyticsFilter::class,
			],
		)
	]
)
@ActiveProfiles("test")
class RootControllerTest {

	@Autowired
	lateinit var mockMvc: MockMvc

	@MockitoBean
	lateinit var blogService: BlogService

	@Test
	fun `index page is accessible`() {
		val post = BlogPostModel(
			name = "sample-post",
			title = "Sample Post",
			content = "Markdown **content**"
		)
		`when`(blogService.getAllBlogPosts()).thenReturn(listOf(post))

		mockMvc.get("/")
			.andExpect {
				status { isOk() }
				view { name("index") }
				model { attributeExists("blogPosts") }
			}

	}

}