@file:OptIn(ExperimentalUuidApi::class)

package io.github.michael_bailey.spring_blog.authentication

import bitlib.serialiser.UuidSerialiser
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class SessionCookie(
	@Serializable(with = UuidSerialiser::class)
	val id: Uuid = Uuid.random()
)