package io.github.michael_bailey.spring_blog.entity

import bitlib.authentication.IViewerContext
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.Query
import org.jetbrains.exposed.v1.jdbc.andWhere
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

/**
 * Query class for retrieving user entities from the database.
 *
 * @param vc the viewer context to be used for privacy checks.
 *
 * todo: add privacy checks
 */
class UserEntityQuery(vc: IViewerContext) : EntityQuery<UserEntity> {

	private var currentQuery: Query =
		UserEntityTable.select(UserEntityTable.id, UserEntityTable.username)

	override fun execute(): List<UserEntity> = transaction {
		currentQuery.map { UserEntity(it) }
	}

	// todo: make this a better API
	fun whereUsernameEquals(username: String): UserEntityQuery {
		currentQuery = currentQuery.andWhere { UserEntityTable.username eq username }
		return this
	}

	override fun tryFirst(): Result<UserEntity> = transaction {
		runCatching {
			UserEntity(currentQuery.first())
		}
	}

	override fun first(): UserEntity? = transaction {
		currentQuery.limit(1).firstOrNull()?.let { UserEntity(it) }
	}

}
