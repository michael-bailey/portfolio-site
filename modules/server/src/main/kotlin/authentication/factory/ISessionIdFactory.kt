package net.michael_bailey.authentication.factory

import kotlin.uuid.Uuid

interface ISessionIdFactory {
	fun create(): Uuid
}