@file:Project("authentication")

package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext
import jakarta.servlet.http.HttpServletResponse
import net.michael_bailey.metadata.Project
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

@Controller
class LoginController(
	private val vc: IViewerContext,
) {

	@GetMapping("/login")
	fun getLoginPage(
		principal: Principal?,
		res: HttpServletResponse,
	): String {

		// I don't know why principal is null here, It should be [AnonymousPrincipal]
		if (principal != null) {
			res.status = 302
			return "redirect:/admin"
		}

		res.status = 200
		return "login"
	}

}