@file:Project("ktor")

package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.controller.base.RestController
import net.michael_bailey.metadata.Controller
import net.michael_bailey.metadata.GetRoute
import net.michael_bailey.metadata.PostRoute
import net.michael_bailey.metadata.Project

@Controller(basePath = "/api/ktor/test")
class KtorController: RestController() {

	@GetRoute
	fun getKtor(): String {
		return "Get Hello World"
	}

	@PostRoute
	fun postKtor(): String {
		return "Post Hello World"
	}

}