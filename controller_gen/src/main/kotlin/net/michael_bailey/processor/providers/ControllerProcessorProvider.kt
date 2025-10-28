package net.michael_bailey.processor.providers

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider
import net.michael_bailey.processor.processor.ControllerProcessor

class ControllerProcessorProvider: SymbolProcessorProvider {
	override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
		return ControllerProcessor(environment.codeGenerator, environment.logger)
	}
}