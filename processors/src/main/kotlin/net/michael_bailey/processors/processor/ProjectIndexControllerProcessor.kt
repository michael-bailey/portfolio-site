package net.michael_bailey.net.michael_bailey.processors.processor

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFile
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.writeTo
import net.michael_bailey.metadata.Project

class ProjectIndexControllerProcessor(
	private val codeGenerator: CodeGenerator,
	private val logger: KSPLogger,
) : SymbolProcessor {

	companion object Companion {
		private const val PROJECT_INDEX_NAME = "project_index"
	}

	private var generated = false

	private var projectToFilesMap: Map<String, List<String>> = mapOf(
		PROJECT_INDEX_NAME to listOf("ProjectController.kt")
	)

	@OptIn(KspExperimental::class)
	override fun process(resolver: Resolver): List<KSAnnotated> {
		if (generated) return emptyList()

		projectToFilesMap += scanProjectsToFileMap(resolver)

		val controllerClass = TypeSpec.classBuilder("ProjectIndexController").apply {
			addAnnotations(generateControllerAnnotations())
			addProperty(generateFileMapProperty())
			addFunction(generateGetterMethod())
		}.build()

		FileSpec.builder("io.github.michael_bailey.spring_blog", "ProjectIndexController")
			.apply {
				addAnnotation(generateProjectIndexAnnotation())
				addType(controllerClass)
			}.build().writeTo(codeGenerator, aggregating = true)

		generated = true
		return emptyList()
	}

	@OptIn(KspExperimental::class)
	private fun scanProjectsToFileMap(resolver: Resolver): Map<String, List<String>> =
		resolver.getSymbolsWithAnnotation("net.michael_bailey.metadata.Project")
			.filter { it is KSFile }.map { it as KSFile }.fold(mapOf()) { acc, file ->

				val projectName = file.getAnnotationsByType(Project::class).first().name
				val fileList = acc[projectName] ?: listOf()
				val newFileList = fileList + file.fileName

				acc + mapOf(projectName to newFileList)
			}

	private fun generateFileMapProperty() = PropertySpec.builder(
		"projectMap", MAP.parameterizedBy(STRING, LIST.parameterizedBy(STRING))
	).apply {

		val codeBlock = CodeBlock.builder().apply {
			add("mapOf(\n")
			projectToFilesMap.forEach { (projectName, fileList) ->
				add("%S to listOf(\n", projectName)
				fileList.forEachIndexed { index, fileName ->
					if (index != 0) add(",\n")
					add("\t%S", fileName)
				}
				add("\n),\n")
			}
			add(")\n")
		}.build()

		initializer(codeBlock)

	}.build()

	private fun generateGetterMethod() =
		FunSpec.builder(name = "getAllProjects").apply {
			addAnnotation(
				generateRouteAnnotation()
			)
			addStatement("return projectMap")
			returns(MAP.parameterizedBy(STRING, LIST.parameterizedBy(STRING)))

		}.build()

	private fun generateControllerAnnotations(): List<AnnotationSpec> = listOf(
		AnnotationSpec.builder(
			ClassName(
				"org.springframework.web.bind.annotation", "RestController"
			)
		).build(),
		AnnotationSpec.builder(
			ClassName(
				"org.springframework.web.bind.annotation", "RequestMapping"
			)
		).addMember("\"/api/projects\"").build(),
	)

	private fun generateRouteAnnotation(): AnnotationSpec =
		AnnotationSpec.builder(
			ClassName(
				"org.springframework.web.bind.annotation", "GetMapping"
			)
		).addMember("\"/\"").build()

	private fun generateProjectIndexAnnotation() = AnnotationSpec.builder(
		ClassName(
			"net.michael_bailey.metadata", "Project"
		)
	).addMember("name = \"$PROJECT_INDEX_NAME\"").build()

}