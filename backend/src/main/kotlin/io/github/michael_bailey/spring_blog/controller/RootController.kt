@file:Project("blog")

package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.service.BlogService
import net.michael_bailey.metadata.Project
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping


/**
 * Controller that handles root-level web requests and prepares data for the homepage.
 * It fetches blog post summaries and injects them into the model for rendering.
 */
@Controller
class RootController(
	private val blogService: BlogService,

) {

	/**
	 * Handles requests to the root path ("/").
	 *
	 * @param model the Spring model used to pass attributes to the view.
	 * @return the name of the Thymeleaf template to render.
	 */
	@GetMapping("/")
	fun index(model: Model): String {
		val blogPosts = blogService.getAllBlogPosts()
		model.addAttribute("blogPosts", blogPosts)
		return "index"
	}

}