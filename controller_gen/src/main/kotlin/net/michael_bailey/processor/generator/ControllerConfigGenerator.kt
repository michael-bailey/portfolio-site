@file:OptIn(KspExperimental::class)

package net.michael_bailey.processor.generator

import com.google.devtools.ksp.*
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSAnnotation
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.google.devtools.ksp.symbol.Visibility
import net.michael_bailey.controller.action.IActionResult
import net.michael_bailey.controller.annotations.Controller
import net.michael_bailey.controller.annotations.GetRoute
import net.michael_bailey.controller.annotations.PostRoute
import net.michael_bailey.processor.configuration.ControllerConfiguration
import net.michael_bailey.processor.configuration.RouteConfiguration

class ControllerConfigGenerator(private val logger: KSPLogger) {

	companion object {
		private val ACTION_RESULT_NAME = IActionResult::class.qualifiedName!!
	}

	fun generate(
		controllerDecl: KSClassDeclaration,
	): net.michael_bailey.processor.configuration.ControllerConfiguration {

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
			path = path,
			methodName = methodName,
			httpMethod = httpMethod,
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

		var isValid = true

		if (!function.annotations.any(::isRouteAnnotation)) return false

		if (function.isAbstract) {
			logger.error("${function.simpleName.asString()} is abstract, abstract functions cannot be handlers")
			isValid = false
		}

		if (function.isConstructor()) {
			logger.error("${function.simpleName.asString()} is a constructor, constructors cannot be handlers")
			isValid = false
		}

		if (function.getVisibility() != Visibility.PUBLIC) {
			logger.error("${function.simpleName.asString()} is not public, only public functions can be handlers")
			isValid = false
		}

		if (function.returnType?.resolve()?.declaration?.qualifiedName?.asString() != ACTION_RESULT_NAME) {
			logger.error("${function.simpleName.asString()} does not return IActionResult, only functions that return IActionResult can be handlers")
			isValid = false
		}

		return isValid
	}

	private fun isRouteAnnotation(annotation: KSAnnotation): Boolean =
		annotation.shortName.asString().let {
			it == GetRoute::class.simpleName || it == PostRoute::class.simpleName
		}
}
