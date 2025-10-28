package io.github.michael_bailey.spring_blog.scope

import org.koin.core.component.KoinScopeComponent
import org.koin.core.component.createScope
import org.koin.core.scope.Scope

class AuthenticationScope: KoinScopeComponent {
	override val scope: Scope by lazy { createScope(this) }

	fun close() = scope.close()
}