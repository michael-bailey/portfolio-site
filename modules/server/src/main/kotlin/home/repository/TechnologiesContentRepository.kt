package net.michael_bailey.home.repository

import net.michael_bailey.home.model.ContentSection
import net.michael_bailey.home.model.ParagraphArticle
import org.koin.core.annotation.Single

@Single
class TechnologiesContentRepository {
	fun getContentSections(): List<ContentSection> = listOf(
		ContentSection(
			header = "Technologies", description = "", articles = listOf(
				kotlinArticle(),
				ktorArticle(),
				multiplatformArticle(),
				rustArticle(),
				elixirArticle(),
			)
		),
	)

	private fun elixirArticle(): ParagraphArticle = ParagraphArticle(
		header = "Elixir", paragraphs = listOf(
			"""I don't use this language as much as I would like. However, I did want to mention it
				for some of the principles it follows.""",
			"""First, let it crash. Most programming is done defensively, try statements, 
				if guard blocks, etc. Elixir discards this mentality. The principle is, 
				fail early, log the issue, reset the state to a fresh slate. This allows 
				errors to be flagged quicker, and program state to be saved. This combined 
				with the supervision model below allows errors to be rectified easier.""",
			"""
				The second principle follows from the usage of "processes". These are green threads,
				and Erlang / Elixir uses them everywhere. The second principle is supervise and recover.
				A supervisor is a process that monitors child processes. When a child process dies, the
				supervisor is notified and decides what to do next. This gives 'let it crash' the
				ability to recover quickly, without taking the entire program down.
				""".trimIndent(),
			"""
				Because of these principles, and the Elixir language itself, I think it's worth mentioning.
				As its philosophy, whilst not mapping perfectly onto kotlin or rust, can be used to make 
				decisions around architecture.
			""".trimIndent()
		)
	)

	private fun rustArticle(): ParagraphArticle = ParagraphArticle(
		header = "Rust", paragraphs = listOf(
			"""Rust is my current go to for projects that have well defined states.
				This is because of a number of it's features that focus on provable correctness.
			""".trimIndent(), """
				As a systems language, it is fast. Rust compiles to machine code directly.
				So when compiled with optimisations, it is faster than the JVM, Python
				or other virtual machine languages. This also means that a lot of memory 
				management is 'manual', but can be automatic when explicitly
				chosen. Overall it doesnt try to hide the inner workings of operations, 
				whilst having an easy to understand std and core library.
			""".trimIndent(), """
				As Rust has a focus on correctness it has an algebraic type system.
				This allows application states to be defined in such a way where
				invalid states can be entirely removed. For example, in Kotlin you 
				have sealed classes. These allow a defined fixed known subset of types at
				compile time. Rust takes this concept further with its enum implementation.
				Overall these changes allow me to eliminate invalid states for my work, 
				whilst forcing states from other frameworks to be transformed into valid 
				states for my use.
			""".trimIndent(), """
				Finally, The compiler (in general) is able to pinpoint where an error is, and often provides fixes where possible. 
				This along with tools like clippy, allows correct and clean code (not the OOP clean).
			""".trimIndent()
		)
	)

	private fun multiplatformArticle(): ParagraphArticle = ParagraphArticle(
		header = "Kotlin Multi-Platform", paragraphs = listOf(
			"""Kotlin multiplatform is the base for end-to-end kotlin applications.
				I use it for almost all my applications, when i need a native application.
				When combined with Ktor and Kotlin compose, it provides the most complete toolset for building full stack applications.
				An example of this is my gym log book app, where the frontend is a desktop and a web application combined into one.
			""".trimIndent()
		)
	)

	private fun ktorArticle(): ParagraphArticle = ParagraphArticle(
		header = "Ktor", paragraphs = listOf(
			"""
				Ktor is a client and server http toolset for kotlin. it is written in kotlin almost entirely, 
				with native parts to match platform specifics. This means i can have a kotlin backend, 
				with a compose and Ktor website, desktop, and native frontends.
			""".trimIndent(), """
				I am a fairly big proponent of the mono-repo. One repo with code, config, tooling and CI/CD.
				Ktor allows me to achieve this. In fact this website is written using Ktor.
				It has a familiar functional api like express, allowing easy extensibility, and readability
			""".trimIndent()
		)
	)

	private fun kotlinArticle(): ParagraphArticle = ParagraphArticle(
		header = "Kotlin", paragraphs = listOf(
			"""Kotlin is my primary programming language.
				This is due to its flexibility between working on backend, frontend, 
				and native applications. Whilst i have experience using industry standard frameworks,
				such as spring boot, i prefer to use Ktor and kotlin multiplatform.
			""".trimIndent()
		)
	)
}