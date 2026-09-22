package net.michael_bailey.authentication.services

import io.ktor.server.sessions.*
import net.michael_bailey.authentication.repository.ISessionRepository
import org.koin.core.annotation.Factory

@Factory(binds = [SessionStorage::class])
class SessionService(
	private val sessionRepository: ISessionRepository,
): SessionStorage {

	override suspend fun write(id: String, value: String) {
		sessionRepository.insert(id, value)
	}

	override suspend fun invalidate(id: String) {
		sessionRepository.remove(id)
	}

	override suspend fun read(id: String): String {
		return sessionRepository.get(id) ?: throw NoSuchElementException()
	}
}