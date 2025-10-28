package io.github.michael_bailey.spring_blog.entity

import org.jetbrains.exposed.v1.core.dao.id.UUIDTable

object SessionEntityTable : UUIDTable("session") {

	val user =
		reference("user_id", UserEntityTable).nullable().index(isUnique = true)

}