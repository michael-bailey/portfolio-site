@file:Project("authentication")

package io.github.michael_bailey.spring_blog.repository


import io.github.michael_bailey.spring_blog.model.UserModel
import net.michael_bailey.metadata.Project
import org.springframework.stereotype.Repository

@Repository
class UserRepository {
	var users = listOf<UserModel>(

	)

	fun findUserByUsername(username: String): UserModel? {
		return users.find { it.username == username }
	}

	fun addNewUser(userModel: UserModel): UserModel {
		users += userModel
		return userModel
	}
}