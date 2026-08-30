package net.michael_bailey.home.repository

import net.michael_bailey.home.model.ContentSection
import net.michael_bailey.home.model.ParagraphArticle
import org.koin.core.annotation.Single

@Single
class TechnologiesContentRepository {
	fun getContentSections(): List<ContentSection> = listOf(
		ContentSection(
			header = "Technologies",
			description = "",
			articles = listOf(
				ParagraphArticle(
					header = "Kotlin",
					paragraphs = listOf(
						"""kotlin is my primary programming language.
							This is due to its flexibility between working on backend, frontend, 
							and native applications. Whilst i have experience using industry standard frameworks,
							such as spring boot, i prefer to use Ktor and kotlin multiplatform.
							""".trimIndent()
					)
				),
				ParagraphArticle(
					header = "Ktor",
					paragraphs = listOf(
						"""
							Ktor is a client and server http toolset for kotlin. it is written in kotlin almost entirely, 
							with native parts to match platform specifics. This means i can have a kotlin backend, 
							with a compose and Ktor website, desktop, and native frontends.
						""".trimIndent(),
						"""
							I am a fairly big proponent of the mono-repo. One repo with code, config, tooling and CI/CD.
							Ktor allows me to achieve this. In fact this website is written using Ktor.
							It has a familioar functional api like express, allowing easy extensibility, and readibility
						""".trimIndent()
					)
				),
				ParagraphArticle(
					header = "Kotlin Multi-Platform",
					paragraphs = listOf(
						"""Kotlin multiplatform is the base for end-to-end kotlin applications.
							I use it for almost all my applications, when i need a native application.
							When combined with Ktor and Kotlin compose, it provides the most complete toolset for building full stack applications.
							An example of this is my gym log book app, where the fromtend is a desktop and a web application combined into one.
						""".trimIndent()
					)
				),
				ParagraphArticle(
					header = "Rust",
					paragraphs = listOf(
						"""Rust is my current go to for projects that have well defined states.
							This is because of a number of it's features that focus on provable correctness.
						""".trimIndent(),
						"""
							As a systems language, it is fast. Rust compiles to machine code directly.
							So when compiled with optimisations, it is faster than the JVM, python
							or other virtual machine languages. This also means that a lot of memory 
							management is also 'manual', but can be automatic when explicitly
							chosen. So it doesnt try to hide the inner workings of operations.
						""".trimIndent(),
						"""
							As rust has a focus on correctness it has an algebraic type system.
							This allows application states to be defined in such a way where
							invalid states can be entirely removed. For example in kotlin, you 
							have sealed classes, which is a way of defining x i find to be the best for describing application state. 
							allowing me to eliminate invalid states for my work, whilst forcing states from other frameworks 
							to be reasoned into valid states for my use.
							Finally, The compiler (in general) is able to pinpoint where an error is, and often provides fixes where possible. 
							This along with tools like clippy, allows correct and clean code (not the OOP clean).
						""".trimIndent()
					)
				),
				ParagraphArticle(
					header = "Elixir",
					paragraphs = listOf(
						"""I dont use this language as much as i would like. However, i did want to mention it
							for some of the principals Entities.it follows.""",
						"""First, let it crash. most programming is done
							defensively, try statements, if guard blocks, etc. Elixir discards this mentality.
							The principal is, fail early, log the issue, reset the state to a fresh slate.
							This allows errors to be flagged quicker, and program state to be saved.
							this combined with the second principal allows errors to be discovered quicker.""",
						"""
							The second principal follows from the usage of "processes". these are green threads,
							and Erlang / Elixir uses them everywhere. the second principal is supervise and recover.
							a supervisor is a process that monitors child processes. When a child process dies, the
							supervisor is notified and decides what to do next. This provides the 'let it crash' its
							ability to not take down the entire program.
							""".trimIndent(),
						"""
							Entities.Because of these principals, and the Elixir language itself, i think it's a good mention.
							As its philosophy, whilst not makking perfectly onto kotlin or rust, can be used to make 
							decisions.
						""".trimIndent()
					)
				),
			)
		),
	)
}