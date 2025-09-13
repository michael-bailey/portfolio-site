@file:Project("administration")

package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.service.BlogService
import jakarta.servlet.http.HttpServletResponse
import net.michael_bailey.metadata.Project
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/admin")
class AdminController(private val blogService: BlogService) {

	private val logger: Logger = LoggerFactory.getLogger(this::class.java)

	@GetMapping
	fun adminIndex(): String {

		logger.info("Rendering admin index page")

		return "adminIndex"
	}
	@PostMapping("/create-post")
	fun createPost(
		@RequestParam name: String,
		@RequestParam title: String,
		@RequestParam content: String,
		res: HttpServletResponse
	): String {
		blogService.createPost(name, title, content)

		res.status = 302


		return "redirect:/"
	}

}