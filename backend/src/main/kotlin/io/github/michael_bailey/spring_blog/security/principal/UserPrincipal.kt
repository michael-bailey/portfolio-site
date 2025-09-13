@file:Project("authentication")

package io.github.michael_bailey.spring_blog.security.principal

import net.michael_bailey.metadata.Project

data class UserPrincipal(
	override val username: String,
	override val roles: List<String>
) : IPrincipal