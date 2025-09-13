package io.github.michael_bailey.spring_blog.ktor

import io.ktor.server.servlet.jakarta.*
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KtorConfig {
	@Bean
	fun ktorBean(): ServletRegistrationBean<ServletApplicationEngine> {
		val servlet = ServletApplicationEngine()
		val registration = ServletRegistrationBean(servlet, "/api/ktor/*")
		// Tell Ktor where to find application.conf on the classpath:
		registration.addInitParameter("io.ktor.ktor.config", "application.conf")

		// All requests under /ktor/* go through Ktor
		return registration
	}
}