@file:Project("blog")

package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.service.BlogService
import io.github.michael_bailey.spring_blog.service.MarkdownService
import jakarta.servlet.http.HttpServletResponse
import net.michael_bailey.metadata.Project
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

/**
 * Controller responsible for handling blog-related routes.
 * It fetches blog content by name and converts Markdown to HTML for display.
 */
@Controller
class BlogController(
	private val blogService: BlogService,
	private val markdownService: MarkdownService
) {

	/**
	 * Handles requests to /blog/ with no blog name.
	 * Returns a 404 error page.
	 *
	 * @param res the HttpServletResponse used to set the status code.
	 * @return the name of the error view to render.
	 */
	@GetMapping("/blog/")
	fun redirectWithNoBlogName(res: HttpServletResponse): String {
		res.status = 404
		return "error"
	}

	/**
	 * Displays a blog post by its name.
	 *
	 * @param name the of the blog post.
	 * @param model the Spring model used to pass attributes to the view.
	 * @return the name of the Thymeleaf template to render.
	 */
	@GetMapping("/blog/{name}")
	fun getBlog(@PathVariable name: String, model: Model, res: HttpServletResponse): String {

		val blogPost = blogService.getByName(name) ?: return redirectWithNoBlogName(res)

		val htmlContent = markdownService.render(blogPost.content)

		val blogPosts = blogService.getAllBlogPosts()

		model.addAttribute("blogPosts", blogPosts)

		model.addAttribute("blogPost", mapOf(
			"title" to blogPost.title,
			"date" to blogPost.date.toString(),
			"content" to htmlContent
		))

		return "blog"
	}
}