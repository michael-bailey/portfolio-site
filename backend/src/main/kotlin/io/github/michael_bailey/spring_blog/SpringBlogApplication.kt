package io.github.michael_bailey.spring_blog

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication()
class SpringBlogApplication

fun main(args: Array<String>) {
	runApplication<SpringBlogApplication>(*args)
}

public fun Application.module() {
	routing {
		route("/api/ktor") {
			get("/") {
				this.call.respondText("Hello World")
			}
		}
	}
}
