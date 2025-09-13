@file:Project("projects")

package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.service.BlogService
import net.michael_bailey.metadata.Project
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/project")
class ProjectController(
	private val blogService: BlogService,
) {

	@GetMapping("/gym")
	fun `Gym log book`(
		model: Model
	): String {

		val blogPosts = blogService.getAllBlogPosts()

		model.addAttribute("blogPosts", blogPosts)

		return "gym_log_book"
	}

}