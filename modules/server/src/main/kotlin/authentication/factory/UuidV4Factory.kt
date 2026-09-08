package net.michael_bailey.authentication.factory

import org.koin.core.annotation.Single
import kotlin.uuid.Uuid

@Single(binds = [ISessionIdFactory::class])
class UuidV4Factory: ISessionIdFactory {
	override fun create(): Uuid = Uuid.generateV4()
}