@file:OptIn(ExperimentalUuidApi::class)

package io.github.michael_bailey.spring_blog.entity

import bitlib.authentication.IViewerContext
import org.jetbrains.exposed.v1.core.ResultRow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlin.uuid.toKotlinUuid

class UserEntity(private val data: ResultRow) {

	val id: Uuid get() = data[UserEntityTable.id].value.toKotlinUuid()
	val username: String get() = data[UserEntityTable.username]
	val password: String get() = data[UserEntityTable.password]

	companion object {
		fun query(vc: IViewerContext): UserEntityQuery = UserEntityQuery(vc)
	}

	fun toData(): UserEntityData = UserEntityData(
		id = id,
		username = username
	)

}