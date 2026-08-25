package net.michael_bailey.home.repository

import net.michael_bailey.home.model.ContentArticle
import net.michael_bailey.home.model.ContentSection
import org.koin.core.annotation.Single

@Single
class TechnologiesContentRepository {
	fun getContentSections(): List<ContentSection> = listOf(
		ContentSection(
			header = "Technologies",
			description = "",
			articles = listOf(
				ContentArticle(
					header = "Kotlin",
					paragraphs = listOf(
						"""kotlin is my primary programming language.
							This is due to its flexibility between working on backend, frontend, 
							and native applications. Whilst i have experience using industry standard frameworks,
							such as spring boot, i prefer to use Ktor and kotlin multiplatform.
							""".trimIndent()
					)
				),
				ContentArticle(
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
				ContentArticle(
					header = "Kotlin Multi-Platform",
					paragraphs = listOf(
						"""Kotlin multiplatform is the base for end-to-end kotlin applications.
							I use it for almost all my applications, when i need a native application.
							When combined with Ktor and Kotlin compose, it provides the most complete toolset for building full stack applications.
							An example of this is my gym log book app, where the fromtend is a desktop and a web application combined into one.
						""".trimIndent()
					)
				),
				ContentArticle(
					header = "Rust",
					paragraphs = listOf(
						"""Rust is my current go to for projects that have well defined states.
							This is because of a number of it's features. 
							Firstly, as a system language, it doesnt try to hide the inner workings of operations.
							Secondly its algebraic type system, i find to be the best for describing application state. 
							allowing me to eliminate invalid states for my work, whilst forcing states from other frameworks 
							to be reasoned into valid states for my use.
							Finally, The compiler (in general) is able to pinpoint where an error is, and often provides fixes where possible. 
							This along with tools like clippy, allows correct and clean code (not the OOP clean). 
							""".trimIndent()
					)
				),
				ContentArticle(
					header = "Elixir",
					paragraphs = listOf(
						"""I dont use this language as much as i would like. However, i did want to mention it
							for some of the principals it follows.""",
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
							Because of these principals, and the Elixir language itself, i think it's a good mention.
							As its philosophy, whilst not makking perfectly onto kotlin or rust, can be used to make 
							decisions.
						""".trimIndent()
					)
				),
			)
		),
	)
}