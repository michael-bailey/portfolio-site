@file:OptIn(KspExperimental::class)

package net.michael_bailey.processors.generator

import com.google.devtools.ksp.*
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.Visibility
import net.michael_bailey.metadata.Controller
import net.michael_bailey.metadata.GetRoute
import net.michael_bailey.metadata.PostRoute
import net.michael_bailey.processors.configuration.ControllerConfiguration
import net.michael_bailey.processors.configuration.RouteConfiguration

class ControllerConfigGenerator(private val logger: KSPLogger) {
	fun generate(
		controllerDecl: KSClassDeclaration,
	): ControllerConfiguration {

		logger.info("generating config for controller: ${controllerDecl.qualifiedName?.asString()}")

		val controllerAnnotation =
			controllerDecl.getAnnotationsByType(Controller::class).first()

		logger.info("controller annotation: $controllerAnnotation with base path: ${controllerAnnotation.basePath}")

		val routes =
			controllerDecl.getDeclaredFunctions().filter(::routeMethodsFilter)
				.map(::generateControllerRouteConfiguration)

		return ControllerConfiguration(
			basePath = controllerAnnotation.basePath,
			qualifiedName = controllerDecl.qualifiedName!!.asString(),
			routes = routes.toList()
		)
	}

	@OptIn(KspExperimental::class)
	private fun generateControllerRouteConfiguration(function: KSFunctionDeclaration): RouteConfiguration {
		val annotations = function.annotations.filter(::isRouteAnnotation)

		val path = annotations.map(::getPathFromRouteAnnotation).first()

		val methodName = function.simpleName.asString()

		val httpMethod = getHttpMethodFromAnnotation(annotations.first())

		return RouteConfiguration(
			path = path, methodName = methodName, httpMethod = httpMethod,
			controllerName = function.parentDeclaration?.qualifiedName?.asString()!!
		)
	}

	private fun getPathFromRouteAnnotation(annotation: KSAnnotation): String =
		annotation.arguments.first { arg -> arg.name?.asString() == "path" }.value as String

	// TODO: Add support for other HTTP methods, class for handling route annotations
	private fun getHttpMethodFromAnnotation(annotation: KSAnnotation): String =
		when (annotation.shortName.asString()) {
			GetRoute::class.simpleName -> "GET"
			PostRoute::class.simpleName -> "POST"
			else -> throw IllegalArgumentException("Unsupported HTTP method annotation ${annotation.shortName}")
		}

	private fun routeMethodsFilter(function: KSFunctionDeclaration): Boolean {
		val isRoute = !function.isConstructor() && function.getVisibility() == Visibility.PUBLIC && function.annotations.any(
			::isRouteAnnotation
		)

		logger.info("checking function: ${function.simpleName.asString()} is route: $isRoute")

		return isRoute
	}

	private fun isRouteAnnotation(annotation: KSAnnotation): Boolean =
		annotation.shortName.asString().let {
			it == GetRoute::class.simpleName || it == PostRoute::class.simpleName
		}


}
