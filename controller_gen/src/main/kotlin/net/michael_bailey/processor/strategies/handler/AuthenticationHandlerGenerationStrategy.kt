package net.michael_bailey.processor.strategies.handler

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FunSpec
import io.ktor.server.routing.*
import net.michael_bailey.processor.configuration.ControllerConfiguration
import net.michael_bailey.processor.configuration.RouteConfiguration
import net.michael_bailey.processor.strategies.naming.INamingStrategy
import java.util.Locale.getDefault

class AuthenticationHandlerGenerationStrategy(
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

			beginControlFlow("""${routeConfiguration.httpMethod.lowercase(getDefault())}("${routeConfiguration.path}")""")
			add(
				"""
						val requestScope = RequestScope()
						val controller = ${routeConfiguration.controllerName}()
						
						controller.vc = call.principal()!!
						controller.scope = requestScope.scope
						
						val res = controller.${routeConfiguration.methodName}()
						
						res.executeResult(call)
				""".trimIndent()
			)
			endControlFlow()

			build().let(::addCode)
		}
	}.build()

	override fun generateSetup(controllerConfiguration: ControllerConfiguration): FunSpec =
		FunSpec.builder(
			namingStrategy.getControllerSetupName(controllerConfiguration)
		).apply {
			receiver(Routing::class)

			beginControlFlow("route(\"${controllerConfiguration.basePath}\")")
			beginControlFlow("authenticate()")

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
			endControlFlow()
		}.build()

}