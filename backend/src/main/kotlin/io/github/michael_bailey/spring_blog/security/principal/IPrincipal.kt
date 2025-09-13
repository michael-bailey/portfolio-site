@file:Project("authentication")

package io.github.michael_bailey.spring_blog.security.principal

import net.michael_bailey.metadata.Project
import java.security.Principal

sealed interface IPrincipal : Principal {
	val username: String
	val roles: List<String>

	override fun getName(): String = username
}