package net.michael_bailey.processor.configuration

data class RouteConfiguration(
	val path: String,
	val methodName: String,
	val httpMethod: String,
	val controllerName: String,
)