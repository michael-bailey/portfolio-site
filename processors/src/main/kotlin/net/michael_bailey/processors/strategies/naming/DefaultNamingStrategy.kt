package net.michael_bailey.processors.strategies.naming

import net.michael_bailey.processors.configuration.ControllerConfiguration
import net.michael_bailey.processors.configuration.RouteConfiguration

class DefaultNamingStrategy: INamingStrategy {

	override fun getRouteFunctionName(
		controllerConfiguration: ControllerConfiguration,
		routeConfiguration: RouteConfiguration,
	): String {
		return "${routeConfiguration.httpMethod.lowercase()}_${routeConfiguration.methodName}"
	}

	override fun getControllerSetupName(
		controllerConfiguration: ControllerConfiguration,
	): String {
		return "setup${controllerConfiguration.qualifiedName.split(".").last()}Controller"
	}

	override fun getFilename(controllerConfiguration: ControllerConfiguration): String {
		return "${controllerConfiguration.qualifiedName.split(".").last()}_generated"
	}

}