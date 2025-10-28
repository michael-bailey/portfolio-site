package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.config.WebSecurityConfig
import io.github.michael_bailey.spring_blog.controller.old.LoginController
import io.github.michael_bailey.spring_blog.filter.AnalyticsFilter
import io.github.michael_bailey.spring_blog.filter.ViewerContextFilter
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.Import
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

@WebMvcTest(
	controllers = [LoginController::class],
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
class LoginControllerTest {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Test
	fun `unauthenticated user should see login page`() {
		mockMvc.get("/login")
			.andExpect {
				status { isOk() }
				view().name("login")
			}
	}

	@Test
	@WithMockUser(username = "TestUser", roles = ["ADMIN"])
	fun `authenticated user should be redirected to admin`() {
		mockMvc.get("/login")
			.andExpect {
				status { is3xxRedirection() }
				redirectedUrl("/admin")
			}
	}
}