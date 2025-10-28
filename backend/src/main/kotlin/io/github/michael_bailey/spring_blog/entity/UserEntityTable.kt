package io.github.michael_bailey.spring_blog.entity

import org.jetbrains.exposed.v1.core.dao.id.UUIDTable

/**
 * Table definition for the users table.
 *
 * @see UserEntity
 */
object UserEntityTable: UUIDTable("user") {

	val username = varchar("username", 255)
	val password = varchar("password", 255)

}