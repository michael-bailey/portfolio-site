package net.michael_bailey.processors.strategies.naming

import net.michael_bailey.processors.configuration.ControllerConfiguration
import net.michael_bailey.processors.configuration.RouteConfiguration

interface INamingStrategy {

	fun getControllerSetupName(
		controllerConfiguration: ControllerConfiguration,
	): String

	fun getRouteFunctionName(
		controllerConfiguration: ControllerConfiguration,
		routeConfiguration: RouteConfiguration,
	): String

	fun getFilename(
		controllerConfiguration: ControllerConfiguration,
	): String
}