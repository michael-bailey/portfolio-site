package net.michael_bailey.processor.configuration

data class ControllerConfiguration(
	val basePath: String,
	val qualifiedName: String,
	val routes: List<net.michael_bailey.processor.configuration.RouteConfiguration>
)
