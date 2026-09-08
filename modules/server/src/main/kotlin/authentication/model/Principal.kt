package net.michael_bailey.authentication.model

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
sealed class Principal {
	@Serializable
	data class Guest(val host: String) : Principal()

	@Serializable
	data class User(val id: Uuid) : Principal()

	@Serializable
	data class Service(val permissions: Map<Permission, PermissionLevel>) : Principal()
}