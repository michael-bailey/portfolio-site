package io.github.michael_bailey.spring_blog.setup

import io.github.michael_bailey.spring_blog.entity.SessionEntityTable
import io.github.michael_bailey.spring_blog.entity.UserEntityTable
import io.ktor.server.application.*
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun Application.setupDatabase() {
	Database.connect(
		url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
		driver = "org.h2.Driver"
	)

	transaction {
		SchemaUtils.create(
			SessionEntityTable,
			UserEntityTable
		)
	}
}