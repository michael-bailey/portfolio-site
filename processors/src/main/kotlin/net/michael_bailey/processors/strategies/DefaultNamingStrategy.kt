package net.michael_bailey.processors.strategies

import net.michael_bailey.processors.configuration.ControllerConfiguration
import net.michael_bailey.processors.configuration.RouteConfiguration

class DefaultNamingStrategy: INamingStrategy {
	override fun getControllerSetupName(
		controllerConfiguration: ControllerConfiguration,
	): String {
		return "setup${controllerConfiguration.qualifiedName.split(".").last()}Controller"
	}

	override fun getControllerRouteName(
		controllerConfiguration: ControllerConfiguration,
		routeConfiguration: RouteConfiguration,
	): String {
		return "${routeConfiguration.httpMethod.lowercase()}${routeConfiguration.methodName.replaceFirstChar { it.uppercase()}}"
	}

	override fun getMappingName(controllerConfiguration: ControllerConfiguration): String {
		return "mapping${controllerConfiguration.qualifiedName.split(".").last()}Controller"
	}

}