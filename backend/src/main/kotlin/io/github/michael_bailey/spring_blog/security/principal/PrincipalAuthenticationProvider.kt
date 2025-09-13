@file:Project("authentication")

package io.github.michael_bailey.spring_blog.security.principal

import io.github.michael_bailey.spring_blog.repository.UserRepository
import net.michael_bailey.metadata.Project
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class PrincipalAuthenticationProvider(
	private val userRepository: UserRepository,
	private val passwordEncoder: PasswordEncoder,
) : AuthenticationProvider {

	private val logger = LoggerFactory.getLogger(this::class.java)

	override fun authenticate(authentication: Authentication?): Authentication? {

		logger.info("Authenticating user")

		logger.info("getting authentication $authentication")

		val username = authentication?.name
			?: throw BadCredentialsException("No username provided")
		val rawPassword = authentication.credentials.toString()

		val user = userRepository.findUserByUsername(username)
			?: throw BadCredentialsException("User not found")

		if (!passwordEncoder.matches(rawPassword, user.password)) {
			throw BadCredentialsException("Invalid credentials")
		}

		val principal = UserPrincipal(
			username = username,
			roles = user.getRoles()
		)

		return UsernamePasswordAuthenticationToken(
			principal,
			null,
			user.getRoles().map { SimpleGrantedAuthority(it) }
		)
	}

	override fun supports(authentication: Class<*>): Boolean {

		logger.info("Checking if authentication provider supports $authentication")

		return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(
			authentication
		) || AnonymousPrincipal::class.java.isAssignableFrom(authentication)
	}

}