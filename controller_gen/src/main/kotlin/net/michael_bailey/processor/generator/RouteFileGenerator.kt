package net.michael_bailey.processor.generator

import com.google.devtools.ksp.processing.KSPLogger
import com.squareup.kotlinpoet.FileSpec
import net.michael_bailey.processor.configuration.ControllerConfiguration
import net.michael_bailey.processor.strategies.handler.IHandlerGenerationStrategy
import net.michael_bailey.processor.strategies.naming.INamingStrategy


class RouteFileGenerator(
	private val namingStrategy: INamingStrategy,
	private val handlerGenerationStrategy: IHandlerGenerationStrategy,
	private val logger: KSPLogger
) {

	fun generate(
		controllerConfiguration: ControllerConfiguration,
	): FileSpec {

		logger.info("Generating function for controller routes of: ${controllerConfiguration.qualifiedName}")

		val handlerFunctions = controllerConfiguration.routes.map {
			handlerGenerationStrategy.generateHandler(
				controllerConfiguration,
				it
			)
		}

		logger.info("Generated ${handlerFunctions.size} handler functions specs")

		val setupFunction = handlerGenerationStrategy.generateSetup(controllerConfiguration)

		logger.info("Generated setup function")

		val fileSpec = FileSpec.builder("io.github.michael_bailey.spring_blog", namingStrategy.getFilename(controllerConfiguration)).apply {

			addImport("io.ktor.server.routing", "get", "post", "route")
			addImport("io.ktor.server.auth", "principal", "authenticate")
			addImport("io.github.michael_bailey.spring_blog.scope", "RequestScope")

			(handlerFunctions + listOf(setupFunction)).forEach(::addFunction)
		}.build()

		return fileSpec
	}
}
