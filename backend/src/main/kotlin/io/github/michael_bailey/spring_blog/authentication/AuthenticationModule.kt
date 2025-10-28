package io.github.michael_bailey.spring_blog.authentication

import io.github.michael_bailey.spring_blog.scope.AuthenticationScope
import io.github.michael_bailey.spring_blog.scope.RequestScope
import org.koin.dsl.module

val AuthenticationModule = module {
	scope<RequestScope> {
		scoped { AuthenticationScope() }
	}
	scope<AuthenticationScope> {

	}
}