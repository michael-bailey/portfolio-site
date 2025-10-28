package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.config.WebSecurityConfig
import io.github.michael_bailey.spring_blog.controller.old.BlogController
import io.github.michael_bailey.spring_blog.filter.AnalyticsFilter
import io.github.michael_bailey.spring_blog.filter.ViewerContextFilter
import io.github.michael_bailey.spring_blog.model.BlogPostModel
import io.github.michael_bailey.spring_blog.service.BlogService
import io.github.michael_bailey.spring_blog.service.MarkdownService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(
	controllers = [BlogController::class],
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
@Import(WebSecurityConfig::class)
@ActiveProfiles("test")
class BlogControllerTest {

	@Autowired
	lateinit var mockMvc: MockMvc

	@MockitoBean
	lateinit var blogService: BlogService

	@MockitoBean
	lateinit var markdownService: MarkdownService

	@Test
	fun `getBlog returns blog view when post exists`() {
		val post = BlogPostModel(
			name = "sample-post",
			title = "Sample Post",
			content = "Markdown **content**"
		)
		`when`(blogService.getByName("sample-post")).thenReturn(post)
		`when`(blogService.getAllBlogPosts()).thenReturn(listOf(post))
		`when`(markdownService.render("Markdown **content**")).thenReturn("<p>Markdown <strong>content</strong></p>")

		mockMvc.get("/blog/sample-post")
			.andExpect {
				status { isOk() }
				view { name("blog") }
				model { attributeExists("blogPost", "blogPosts") }
			}
	}

	@Test
	fun `getBlog returns error view when post does not exists`() {

		`when`(blogService.getByName("sample-post")).thenReturn(null)
		`when`(blogService.getAllBlogPosts()).thenReturn(listOf())
		`when`(markdownService.render("Markdown **content**")).thenReturn("<p>Markdown <strong>content</strong></p>")

		mockMvc.get("/blog/sample-post")
			.andExpect {
				status { isNotFound() }
				view { name("error") }
			}
	}
}