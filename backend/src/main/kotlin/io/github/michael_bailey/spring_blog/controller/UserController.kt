package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.entity.UserEntity
import net.michael_bailey.controller.action.IActionResult
import net.michael_bailey.controller.annotations.Controller
import net.michael_bailey.controller.annotations.GetRoute
import net.michael_bailey.controller.base.RestController

@Controller(basePath = "/api/user")
class UserController : RestController() {

	@GetRoute("/all")
	fun getUsers(): IActionResult {

		val users = UserEntity.query(vc)
			.execute()
			.map(UserEntity::toData)

		return json(users)
	}

	override fun preExec() {
		TODO("Not yet implemented")
	}

	override fun postExec() {
		TODO("Not yet implemented")
	}


}