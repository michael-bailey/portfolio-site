package net.michael_bailey.processors.strategies

import net.michael_bailey.processors.configuration.ControllerConfiguration
import net.michael_bailey.processors.configuration.RouteConfiguration

interface INamingStrategy {

	fun getControllerSetupName(
		controllerConfiguration: ControllerConfiguration,
	): String

	fun getControllerRouteName(
		controllerConfiguration: ControllerConfiguration,
		routeConfiguration: RouteConfiguration,
	): String

	fun getMappingName(
		controllerConfiguration: ControllerConfiguration,
	): String
}