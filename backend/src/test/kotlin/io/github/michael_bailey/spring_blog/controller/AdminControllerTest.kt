package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.config.WebSecurityConfig
import io.github.michael_bailey.spring_blog.controller.old.AdminController
import io.github.michael_bailey.spring_blog.filter.AnalyticsFilter
import io.github.michael_bailey.spring_blog.filter.ViewerContextFilter
import io.github.michael_bailey.spring_blog.service.BlogService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.Import
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

@WebMvcTest(
	controllers = [AdminController::class],
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
class AdminControllerTest {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@MockitoBean
	lateinit var blogService: BlogService

	@Test
	fun `Attempt to load admin panel when logged out`() {
		mockMvc.get("/admin")
			.andExpect {
				status { isForbidden() }
			}
	}

	@Test
	@WithMockUser(username = "TestUser", authorities = ["ADMIN"])
	fun `Attempt to load admin panel when logged in`() {
		mockMvc.get("/admin")
			.andExpect {
				status { isOk() }
				view().name("adminIndex")
			}
	}

	@Test
	fun `Attempt to create post when logged out`() {
		mockMvc.post("/admin/create-post")
			.andExpect {
				status { isForbidden() }
			}
	}

	@Test
	@WithMockUser(username = "TestUser", authorities = ["ADMIN"])
	fun `Creates new article`() {

		val name = "test post"
		val title = "test post title"
		val content = "# Test post"

		`when`(blogService.createPost(
			name = name,
			title = title,
			content = content
		)).thenReturn(null)
		
		mockMvc.post("/admin/create-post") {
			formField("name", name)
			formField("title", title)
			formField("content", content)
		}.andExpect {
			status { redirectedUrl("/") }
			view().name("/")
		}
	}


}