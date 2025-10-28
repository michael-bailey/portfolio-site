package net.michael_bailey.processor.scanners

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.validate
import net.michael_bailey.controller.annotations.Controller
import net.michael_bailey.controller.base.IController

/**
 * Scans for controllers in the project.
 *
 * @property resolver The resolver used to scan for controllers.
 * @property logger The logger used to log scan results.
 *
 * todo: add caching
 */
final class ControllerScanner(
	private val resolver: Resolver,
	private val logger: KSPLogger,
) {

	companion object {

		val CONTROLLER_ANNOTATION_NAME = Controller::class.qualifiedName!!

		val CONTROLLER_BASE_INTERFACE_NAME = IController::class.qualifiedName!!
	}

	private var controllerClasses: MutableMap<String, KSClassDeclaration> =
		mutableMapOf()

	var deferred: List<KSClassDeclaration> = emptyList()

	fun getControllers(): List<KSClassDeclaration> {

		logger.info("Starting controller scan")

		val symbols = resolver.getSymbolsWithAnnotation(CONTROLLER_ANNOTATION_NAME)

		val (validated, deferred) = symbols.partition { it.validate() }

		logger.info("got ${validated.size} validated controllers, ${deferred.size} deferred")

		this.deferred = deferred.filterIsInstance<KSClassDeclaration>()

		logger.info("set deferred to ${deferred.size}")

		controllerClasses += validated.filterIsInstance<KSClassDeclaration>()
			.filterNot(::isControllerCached).filter(::isValidControllerType)
			.associateBy { it.qualifiedName!!.asString() }

		if (controllerClasses.isEmpty()) {
			logger.warn(
				"No controllers were found, please check annotations if this isn't meant to be the case",
				null
			)
			return emptyList()
		}

		logger.info("Found ${controllerClasses.size} controllers, returning")

		return controllerClasses.values.toList()

	}

	fun isControllerCached(declaration: KSClassDeclaration): Boolean =
		controllerClasses.contains(declaration.qualifiedName!!.asString())

	private fun isValidControllerType(controllerDecl: KSClassDeclaration): Boolean {

		if (controllerDecl.qualifiedName?.asString() == CONTROLLER_BASE_INTERFACE_NAME) return true

		val superTypes =
			controllerDecl.superTypes.map { it.resolve().declaration as? KSClassDeclaration }
				.filterNotNull()

		if (superTypes.toList().isEmpty()) return false

		return superTypes.any(::isValidControllerType)

	}
}