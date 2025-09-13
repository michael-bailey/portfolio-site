@file:Project("authentication")

package io.github.michael_bailey.spring_blog.security.principal

import net.michael_bailey.metadata.Project

data object AnonymousPrincipal : IPrincipal {
	override val username: String = "anonymousUser"
	override val roles: List<String> = emptyList()

}