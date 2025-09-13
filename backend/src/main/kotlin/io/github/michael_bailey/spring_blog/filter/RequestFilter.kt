@file:Project("infra")

package io.github.michael_bailey.spring_blog.filter

import io.github.michael_bailey.spring_blog.http.CustomHttpRequest
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.michael_bailey.metadata.Project
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@Order(0)
class RequestFilter: OncePerRequestFilter() {

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain
	) {
		val customRequest = CustomHttpRequest(request)
		filterChain.doFilter(customRequest, response)
	}


}