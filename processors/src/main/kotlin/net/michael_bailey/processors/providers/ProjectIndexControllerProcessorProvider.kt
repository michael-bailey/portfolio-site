package net.michael_bailey.net.michael_bailey.processors.providers

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import net.michael_bailey.net.michael_bailey.processors.processor.ProjectIndexControllerProcessor

class ProjectIndexControllerProcessorProvider: SymbolProcessorProvider {
	override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
		return ProjectIndexControllerProcessor(
			environment.codeGenerator,
			environment.logger
		)
	}
}