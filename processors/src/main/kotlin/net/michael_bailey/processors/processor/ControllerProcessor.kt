package net.michael_bailey.processors.processor

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ksp.writeTo
import net.michael_bailey.processors.generator.ControllerConfigGenerator
import net.michael_bailey.processors.generator.RouteFileGenerator
import net.michael_bailey.processors.scanners.ControllerScanner
import net.michael_bailey.processors.strategies.DefaultNamingStrategy
import net.michael_bailey.processors.strategies.INamingStrategy

/**
 * A symbol processor for handling controllers annotated with the `@Controller` annotation.
 *
 * This class generates configuration mappings and functions for controllers and their associated routes,
 * enabling the integration of controllers into a Ktor application routing tree.
 *
 * @constructor Creates a [ControllerProcessor] instance with the specified code generator and logger.
 *
 * @property codeGenerator The code generator used to create generated source files.
 * @property logger The logger used to log messages and errors during the processing.
 */
class ControllerProcessor(
	private val codeGenerator: CodeGenerator,
	private val logger: KSPLogger,
) : SymbolProcessor {

	private var isFinished: Boolean = false

	private val namingStrategy: INamingStrategy = DefaultNamingStrategy()

	/**
	 * Processes the symbols resolved by the Kotlin Symbol Processing (KSP) `Resolver`
	 * and generates controller configurations and routes for Ktor-based applications.
	 * This method retrieves classes annotated with the `@Controller` annotation,
	 * validates them, and generates the corresponding function specifications and setup files.
	 *
	 * @param resolver The KSP `Resolver` used to locate and analyze symbols in the codebase.
	 * @return A list of `KSAnnotated` objects that were unable to be fully processed.
	 */
	@OptIn(KspExperimental::class)
	override fun process(resolver: Resolver): List<KSAnnotated> {
		if (isFinished) return emptyList()

		// todo: insert cache into scanner
		val controllerScanner = ControllerScanner(resolver, logger)
		val configGen = ControllerConfigGenerator(logger)
		val fileGen = RouteFileGenerator(namingStrategy, logger)

		val controllers = controllerScanner.getControllers()
		val (valid, deferred) = controllers.partition { it.validate() }

		if (deferred.isNotEmpty()) return deferred

		val controllerConfigs = valid.map(configGen::generate)
		val routeFiles = controllerConfigs.map(fileGen::generate)

		routeFiles.forEach { file ->
			file.writeTo(codeGenerator, aggregating = true)
		}


		FileSpec.builder("io.github.michael_bailey.spring_blog", "mappedRoutes")
			.apply {

				addImport(
					"io.ktor.server.routing",
					"get",
					"post",
					"put",
					"patch",
					"delete",
					"route",
					"routing"
				)
				addImport("io.ktor.server.response", "respondText")

				addImport("io.ktor.server.application", "Application")


				addFunction(FunSpec.builder("setupControllers").apply {
					receiver(ClassName("io.ktor.server.application", "Application"))

					beginControlFlow("routing")
					controllerConfigs.forEach { controllerConfig ->
						addCode(namingStrategy.getControllerSetupName(controllerConfig) + "()\n")
					}
					endControlFlow()


				}.build())

				build().writeTo(codeGenerator, aggregating = true)
			}

		isFinished = true

		return emptyList()
	}

}