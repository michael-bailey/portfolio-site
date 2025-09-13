@file:Project("privacy")

package io.github.michael_bailey.spring_blog.filter

import io.github.michael_bailey.spring_blog.cookie.PreferenceCookieFactory
import io.github.michael_bailey.spring_blog.http.CustomHttpRequest
import io.github.michael_bailey.spring_blog.privacy.PrivacyPreferencesCookie
import io.github.michael_bailey.spring_blog.security.viewer.ViewerContextFactory
import io.github.michael_bailey.spring_blog.security.viewer.ViewerContextHolder
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.serialization.json.Json
import net.michael_bailey.metadata.Project
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Component
@Order(3)
@OptIn(ExperimentalTime::class)
class ViewerContextFilter(
	private val viewerContextFactory: ViewerContextFactory,
	private val viewerContextHolder: ViewerContextHolder,
	private val clock: Clock,
	private val applicationContext: ApplicationContext,
	private val preferenceCookieFactory: PreferenceCookieFactory,
) : OncePerRequestFilter() {

	private val logger = LoggerFactory.getLogger(ViewerContextFilter::class.java)

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain,
	) {

		request as CustomHttpRequest

		logger.info("ViewerContextFilter started for URI: ${request.requestURI}")

		// Yes, this is encoding to decode, but it's more readable
		val cookieString =
			request.cookies?.find { it.name == PreferenceCookieFactory.PRIVACY_COOKIE_NAME }?.value
				?: PrivacyPreferencesCookie().encode()

		val cookieData =
			runCatching { PrivacyPreferencesCookie.decode(cookieString) }.getOrNull()
				?: Json.decodeFromString<PrivacyPreferencesCookie>(
					cookieString
				)

		val authentication = SecurityContextHolder.getContext().authentication

		val viewerContext = viewerContextFactory.fromAuthentication(
			authentication,
			request,
			clock,
			applicationContext,
			privacyPreferences = cookieData
		)

		viewerContextHolder.context = viewerContext

		logger.info("Viewer context created and set: $viewerContext")

		filterChain.doFilter(request, response)
	}

}
