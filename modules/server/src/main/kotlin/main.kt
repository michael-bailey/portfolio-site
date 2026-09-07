package net.michael_bailey

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.michael_bailey.application.App.setup

fun main() {
	embeddedServer(Netty, port = 8080) {
		this.setup()
	}.start(true)
}