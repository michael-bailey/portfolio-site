package net.michael_bailey.authentication.repository

interface ISessionRepository {
	suspend fun insert(id: String, principal: String)
	suspend fun get(uuid: String): String?
	suspend fun remove(id: String)
}