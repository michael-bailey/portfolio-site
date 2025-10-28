@file:Project("ktor")

package io.github.michael_bailey.spring_blog.controller

import kotlinx.serialization.Serializable
import net.michael_bailey.controller.action.IActionResult
import net.michael_bailey.controller.annotations.Controller
import net.michael_bailey.controller.annotations.GetRoute
import net.michael_bailey.controller.annotations.PostRoute
import net.michael_bailey.controller.base.RestController
import net.michael_bailey.metadata.Project


@Controller(basePath = "/api/ktor/test")
class KtorController() : RestController() {

	@Serializable
	data class TestResponse(
		val message: String,
		val counter: Int,
	)

	@GetRoute
	fun index(): IActionResult {
		TestResponse.serializer()
		return text("Get Hello World")
	}

	@PostRoute
	fun submitIndex(): IActionResult {
		return text("Post Hello World")
	}

	@GetRoute("/json")
	fun jsonTest(): IActionResult = json<TestResponse>(
		TestResponse(
			message = "TODO()", counter = 10
		)
	)

	@GetRoute("/xml")
	fun xmlTest(): IActionResult = xml(
		TestResponse(
			message = "TODO()", counter = 10
		)
	)

	@GetRoute("/redirect")
	fun redirectTest() = redirect("https://michael-bailey.net", true)

	@GetRoute("/notFound")
	fun notFoundTest() = notFound()

	override fun preExec() {
		TODO("Not yet implemented")
	}

	override fun postExec() {
		TODO("Not yet implemented")
	}

}