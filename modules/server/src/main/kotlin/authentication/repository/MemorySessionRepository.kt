package net.michael_bailey.authentication.repository

import net.michael_bailey.authentication.factory.ISessionIdFactory
import org.koin.core.annotation.Single

@Single(binds = [ISessionRepository::class])
class MemorySessionRepository(
	private val sessions: MutableMap<String, String> = HashMap(),
	private val idFactory: ISessionIdFactory
): ISessionRepository {

	override suspend fun insert(id: String, principal: String) {
		sessions[id] = principal
	}

	override suspend fun get(uuid: String): String? {
		return sessions[uuid]?.let { return it }
	}

	override suspend fun remove(id: String) {
		sessions.remove(id)
	}
}