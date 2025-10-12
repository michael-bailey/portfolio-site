package net.michael_bailey.processors.strategies.handler

import com.squareup.kotlinpoet.FunSpec
import net.michael_bailey.processors.configuration.ControllerConfiguration
import net.michael_bailey.processors.configuration.RouteConfiguration

interface IHandlerGenerationStrategy {

	fun generateHandler(
		controllerConfiguration: ControllerConfiguration,
		routeConfiguration: RouteConfiguration,
	): FunSpec

	fun generateSetup(
		controllerConfiguration: ControllerConfiguration,
	): FunSpec

}