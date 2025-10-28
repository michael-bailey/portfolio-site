package io.github.michael_bailey.spring_blog

import io.github.michael_bailey.spring_blog.plugin.RequestScopePlugin
import io.github.michael_bailey.spring_blog.setup.setupAuthentication
import io.github.michael_bailey.spring_blog.setup.setupDatabase
import io.github.michael_bailey.spring_blog.setup.setupKoin
import io.ktor.serialization.kotlinx.xml.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder.json

@SpringBootApplication()
class SpringBlogApplication

fun main(args: Array<String>) {

	embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = { this.module() }).start(wait = true)

//	runApplication<SpringBlogApplication>(*args)
}

fun Application.module() {

	setupDatabase()
	setupKoin()

	install(ContentNegotiation) {
		xml()
		json()
	}

	install(RequestScopePlugin)

	setupAuthentication()

	setupControllers()

}
