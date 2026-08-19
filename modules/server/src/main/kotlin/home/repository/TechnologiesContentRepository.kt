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
						"""kotlin is my primary programming language.
							This is due to its flexibility between working on backend, frontend, 
							and native applications. Whilst i have experience using industry standard frameworks,
							such as spring boot, i prefer to use Ktor and kotlin multiplatform.
							""".trimIndent()
					)
				),
			)
		),
	)
}