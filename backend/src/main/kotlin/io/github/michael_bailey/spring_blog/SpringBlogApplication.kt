package io.github.michael_bailey.spring_blog

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication()
class SpringBlogApplication

fun main(args: Array<String>) {

	embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = { this.module() }).start(wait = true)

//	runApplication<SpringBlogApplication>(*args)
}

fun Application.module() {

	setupControllers()

}
