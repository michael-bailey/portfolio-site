package io.github.michael_bailey.spring_blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication()
class SpringBlogApplication

fun main(args: Array<String>) {
	runApplication<SpringBlogApplication>(*args)
}
