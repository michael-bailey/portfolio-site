@file:Project("analytics")

package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.cookie.PreferenceCookieFactory
import io.github.michael_bailey.spring_blog.http.CustomHttpRequest
import io.github.michael_bailey.spring_blog.privacy.PrivacyPreferencesCookie
import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext
import io.github.michael_bailey.spring_blog.service.AnalyticsService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import net.michael_bailey.metadata.Project
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/analytics")
class AnalyticsPreferencesRestController(
	private val analyticsService: AnalyticsService,
	private val vc: IViewerContext,
) {

	@GetMapping()
	fun getRequestAnalytics(): Map<String, String> {
		return analyticsService.getViewersAnalytics(vc)
	}

	@PostMapping("/preferences")
	fun updatePreferences(
		@RequestParam allowDomainLogging: Boolean? = null,
		@RequestParam allowRequestLogging: Boolean? = null,
		request: CustomHttpRequest,
		response: HttpServletResponse,
	): Boolean {

		val preferenceCookie = request.cookies
				?.find { it.name == PreferenceCookieFactory.PRIVACY_COOKIE_NAME }
				?.let { PrivacyPreferencesCookie.decode(it.value) }
				?: PrivacyPreferencesCookie()

		val cookieValue = preferenceCookie.copy(
			allowedDomainLogging = allowDomainLogging
				?: preferenceCookie.allowedDomainLogging,
			allowedRequestLogging = allowRequestLogging
				?: preferenceCookie.allowedRequestLogging
		)

		val newCookie = Cookie(
			PreferenceCookieFactory.PRIVACY_COOKIE_NAME,
			cookieValue.encode(),
		)

		newCookie.path = "/"

		response.addCookie(newCookie)

		return true
	}


}