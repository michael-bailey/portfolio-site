@file:Project("analytics")

package io.github.michael_bailey.spring_blog.filter

import net.michael_bailey.metadata.Project
import io.github.michael_bailey.spring_blog.http.CustomHttpRequest
import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext
import io.github.michael_bailey.spring_blog.service.AnalyticsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

@Component
@Order(4)
class AnalyticsFilter(
	private val analyticsService: AnalyticsService,
	private val applicationContext: ApplicationContext,
) : OncePerRequestFilter() {

	private val logger: Logger = LoggerFactory.getLogger(this::class.java)

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain,
	) {

		request as CustomHttpRequest

		val vc = applicationContext.getBean(IViewerContext::class.java)

		logger.info("REQUEST_ID: ${vc.requestId}")

		val remoteAddr = request.getHeader("X-Real-IP") ?: request.remoteAddr

		if (vc.privacyPreferences.allowedDomainLogging) {
			logger.info("Logging domain data")
			analyticsService.logRequestDomain(vc.requestId, remoteAddr)
		} else {
			null
		}

		if (vc.privacyPreferences.allowedRequestLogging) {
			logger.info("Logging request data")
			analyticsService.logRequest(
				UUID.fromString(request.requestId),
				request.method,
				request.requestURI
			)
		} else {
			null
		}

		filterChain.doFilter(request, response)
	}
}