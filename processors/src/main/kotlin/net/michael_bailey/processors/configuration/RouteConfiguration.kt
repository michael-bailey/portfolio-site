package net.michael_bailey.processors.configuration

data class RouteConfiguration(
	val path: String,
	val methodName: String,
	val httpMethod: String,
	val controllerName: String,
)