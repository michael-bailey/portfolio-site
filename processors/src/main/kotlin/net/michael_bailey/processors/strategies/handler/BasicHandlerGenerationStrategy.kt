package net.michael_bailey.processors.strategies.handler

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.ktor.server.routing.*
import net.michael_bailey.processors.configuration.ControllerConfiguration
import net.michael_bailey.processors.configuration.RouteConfiguration
import net.michael_bailey.processors.strategies.naming.INamingStrategy
import java.util.Locale.getDefault

class BasicHandlerGenerationStrategy(
	private val namingStrategy: INamingStrategy,
) : IHandlerGenerationStrategy {

	override fun generateHandler(
		controllerConfiguration: ControllerConfiguration,
		routeConfiguration: RouteConfiguration,
	) = FunSpec.builder(
		namingStrategy.getRouteFunctionName(
			controllerConfiguration = controllerConfiguration,
			routeConfiguration = routeConfiguration
		)
	).apply {
		receiver(Route::class)
		CodeBlock.builder().apply {
			add(
				"""
					${routeConfiguration.httpMethod.lowercase(getDefault())}("${routeConfiguration.path}") {
						val controller = ${routeConfiguration.controllerName}()
						val res = controller.${routeConfiguration.methodName}()
						res.executeResult(call)
					}
				""".trimIndent()
			)

			build().let(::addCode)
		}
	}.build()

	override fun generateSetup(controllerConfiguration: ControllerConfiguration): FunSpec =
		FunSpec.builder(
			namingStrategy.getControllerSetupName(controllerConfiguration)
		).apply {
			receiver(Routing::class)

			beginControlFlow("route(\"${controllerConfiguration.basePath}\")")

			controllerConfiguration.routes.forEach { route ->
				addStatement(
					"${
						namingStrategy.getRouteFunctionName(
							controllerConfiguration, route
						)
					}()"
				)
			}

			endControlFlow()
		}.build()

}