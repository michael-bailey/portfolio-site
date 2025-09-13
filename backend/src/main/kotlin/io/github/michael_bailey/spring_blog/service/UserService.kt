@file:Project("authentication")

package io.github.michael_bailey.spring_blog.service

import io.github.michael_bailey.spring_blog.config.WebSecurityConfig
import io.github.michael_bailey.spring_blog.model.UserModel
import io.github.michael_bailey.spring_blog.repository.UserRepository
import net.michael_bailey.metadata.Project
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
	private val userRepository: UserRepository,
	private val config: WebSecurityConfig,
	private val passwordEncoder: PasswordEncoder,
) : UserDetailsService {

	init {
		userRepository.addNewUser(
			UserModel(
				username = config.username!!,
				password = passwordEncoder.encode(config.password!!),
				roles = listOf("USER", "ADMIN"),
				authorities = listOf("ADMIN", "WRITE_PRIVILEGE")
			),
		)
	}

	override fun loadUserByUsername(username: String): UserDetails? {
		return userRepository.findUserByUsername(username) ?: return null
	}
}