package net.michael_bailey.processor.strategies.naming

import net.michael_bailey.processor.configuration.ControllerConfiguration
import net.michael_bailey.processor.configuration.RouteConfiguration

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