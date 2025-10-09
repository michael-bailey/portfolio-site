package net.michael_bailey.processors.strategies

import net.michael_bailey.processors.configuration.ControllerConfiguration
import net.michael_bailey.processors.configuration.RouteConfiguration

interface IRouteNamingStrategy {
	fun getRouteName(
		controllerConfiguration: ControllerConfiguration,
		routeConfiguration: RouteConfiguration,
	): String

	fun getRouteSetupName(
		controllerConfiguration: ControllerConfiguration,
	)
}