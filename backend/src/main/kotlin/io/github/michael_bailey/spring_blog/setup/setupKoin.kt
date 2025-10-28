package io.github.michael_bailey.spring_blog.setup

import io.github.michael_bailey.spring_blog.authentication.AuthenticationModule
import io.github.michael_bailey.spring_blog.modules.DataModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.setupKoin() {
	install(Koin) {
		modules(
			DataModule,
			AuthenticationModule,
		)
	}
}