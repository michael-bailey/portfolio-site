package net.michael_bailey.processors.generator

import com.google.devtools.ksp.processing.KSPLogger
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import io.ktor.server.routing.*
import net.michael_bailey.processors.configuration.ControllerConfiguration
import net.michael_bailey.processors.configuration.RouteConfiguration
import net.michael_bailey.processors.strategies.INamingStrategy
import java.util.Locale.getDefault

class RouteFileGenerator(
	private val namingStrategy: INamingStrategy,
	private val logger: KSPLogger
) {
	fun generate(
		controllerConfiguration: ControllerConfiguration,
	): FileSpec {

		logger.info("Generating function for controller routes of: ${controllerConfiguration.qualifiedName}")

		val functionSpecs = transformToFunctionSpecs(controllerConfiguration)

		logger.info("Generated ${functionSpecs.size} function specs")

		val fileSpec = FileSpec.builder("io.github.michael_bailey.spring_blog", namingStrategy.getMappingName(controllerConfiguration)).apply {

			addImport("io.ktor.server.routing", "get", "post", "route")
			addImport("io.ktor.server.response", "respondText")

			functionSpecs.forEach(::addFunction)
		}.build()

		return fileSpec
	}


	private fun transformToFunctionSpecs(controllerConfiguration: ControllerConfiguration): List<FunSpec> =
		controllerConfiguration.routes.map { generateFunctionSpecForRoute(controllerConfiguration, it) } + createSetupFunctionForController(
			controllerConfiguration
		)

	private fun createSetupFunctionForController(controllerConfiguration: ControllerConfiguration): FunSpec =
		FunSpec.builder(
			namingStrategy.getControllerSetupName(controllerConfiguration)
		).apply {
			receiver(Routing::class)

			beginControlFlow("route(\"${controllerConfiguration.basePath}\")")

			controllerConfiguration.routes.forEach { route ->
				addStatement("${
					namingStrategy.getControllerRouteName(
						controllerConfiguration,
						route
					)
				}()")
			}

			endControlFlow()
		}.build()

	// TODO: Add code to create and controller methods
	private fun generateFunctionSpecForRoute(controllerConfig: ControllerConfiguration, route: RouteConfiguration): FunSpec =
		FunSpec.builder(namingStrategy.getControllerRouteName(controllerConfig, route)).apply {
			receiver(Route::class)
			CodeBlock.builder().apply {
				add(
					"""
					${route.httpMethod.lowercase(getDefault())}("${route.path}") {
						val controller = ${route.controllerName}()
						val res = controller.${route.methodName}()
						call.respondText(res)
					}
				""".trimIndent()
				)

				build().let(::addCode)
			}
		}.build()
}
