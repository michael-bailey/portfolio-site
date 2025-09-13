@file:Project("infra")

package io.github.michael_bailey.spring_blog.filter

import io.github.michael_bailey.spring_blog.http.CustomHttpRequest
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.michael_bailey.metadata.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

@Component
@Order(2)
class RequestIdFilter : OncePerRequestFilter() {

	companion object {
		private const val REQUEST_ID_HEADER: String = "X-Request-ID"
	}

	private val logger: Logger = LoggerFactory.getLogger(this::class.java)

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain
	) {
		val customRequest = request as CustomHttpRequest

		logger.info("Creating request ID")

		val requestId: String = UUID.randomUUID().toString()
		logger.debug("Request ID: $requestId")

		logger.info("Setting request ID header and attribute")

		customRequest.setAttribute(CustomHttpRequest.REQUEST_ID, requestId)
		response.setHeader(REQUEST_ID_HEADER, requestId)

		filterChain.doFilter(customRequest, response)
	}
}