package net.michael_bailey.processor.strategies.handler

import com.squareup.kotlinpoet.FunSpec
import net.michael_bailey.processor.configuration.ControllerConfiguration
import net.michael_bailey.processor.configuration.RouteConfiguration

interface IHandlerGenerationStrategy {

	fun generateHandler(
		controllerConfiguration: ControllerConfiguration,
		routeConfiguration: RouteConfiguration,
	): FunSpec

	fun generateSetup(
		controllerConfiguration: ControllerConfiguration,
	): FunSpec

}