package io.github.michael_bailey.spring_blog.modules

import io.github.michael_bailey.spring_blog.scope.RequestScope
import org.koin.dsl.module

internal val DataModule = module {
	scope<RequestScope>() {

	}
}