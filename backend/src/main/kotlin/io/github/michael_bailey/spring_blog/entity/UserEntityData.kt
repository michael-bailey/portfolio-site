@file:OptIn(ExperimentalUuidApi::class)

package io.github.michael_bailey.spring_blog.entity

import bitlib.serialiser.UuidSerialiser
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class UserEntityData(
	@Serializable(with = UuidSerialiser::class)
	val id: Uuid,
	val username: String,
)
