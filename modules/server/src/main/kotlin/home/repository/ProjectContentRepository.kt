package net.michael_bailey.home.repository

import net.michael_bailey.home.model.ContentSection
import net.michael_bailey.home.model.ParagraphArticle
import org.koin.core.annotation.Single

@Single
class ProjectContentRepository {
	fun getContentSections(): List<ContentSection> = listOf(
		ContentSection(
			header = "Projects",
			description = """Here’s a selection of personal projects I’ve built or continue to
			work on. They aren’t meant to be a complete list, but each one solves
			a problem I’ve personally encountered or adds functionality I found
			missing on my own devices. I'll be writing up stories about some of the
			projects, detailing what I find to be the best things I learnt during
			the
			creation of the projects""".trimIndent(),
			articles = listOf(
				getBramblePiArticle(),
				getGymLogBookArticle(),
				getPersonalWebsiteArticle(),
			)
		),
	)

	private fun getGymLogBookArticle() = ParagraphArticle(
		header = "Gym Log Book", paragraphs = listOf(
			"""Gym Log Book is a full native Android app I built to help log my
				strength training progress. It features structured navigation,
				local data persistence, with plans to display graphs and support
				more exercise types.""",
			"""When I started at the gym, I wanted a way of logging my progress.
				Whist I found lots of apps that could help with this, I found most
				of them to have complex functionality or had lacking UX.
				So I decided to create my own app.""",
			"""After starting with some Apple shortcuts, I got the basic idea of what
				I wanted to include.
				Settings for free weights, or machines; Auto complete for sets; a list
				and possibly graphs for progress.
				All of which expanded my knowledge of Android app development.""",
		)
	)

	private fun getBramblePiArticle() = ParagraphArticle(
		header = "Bramble Pi", paragraphs = listOf(
			"""This is my personal kubernetes cluster. It is a setup of 5 Raspberry Pi
				5's, using K3s to create a 'simple' kubernetes cluster. This has been a
				big learning driver for me, as I've not had much experience with setting
				up deployment environments, managing compute resources, or setting up
				scalable software.""",
			"""The core use cases for this is hosting my own projects, with
				cross-platform ones taking priority. In fact, this very website is
				hosted on it at this moment! the gym log book back and front end is
				hosted on here as well, as its current evolution requires a central
				server, although it is not set up to be the most scalable service in the
				world.""",
			"""There are other project being hosted on this. one is a kind of service
				provisioner, a kind of kubernetes, but only for deploying docker based
				services, this runs on the control plane Pi, as it currently hosts a
				Minecraft server for myself.""",
		)
	)

	private fun getPersonalWebsiteArticle(): ParagraphArticle = ParagraphArticle(
		header = "Portfolio site", paragraphs = listOf(
			"""
				My portfolio site is a place for my thoughts, side projects, and a sandbox
				for testing new things. Whilst this is a testing ground, i have a few rules
				that i try to follow.
			""".trimIndent(), """
				Firstly, this website will not be using javascript frameworks. This is due
				to wanting as pure as possible kotlin backend and frontend. Some sections 
				and projects might feature other languages, but they will be imported from
				CDNs (most likely github) and loaded, using prewritten 'static' scripts
				 as a kind of bootstrap. This allows the strucutre and majority content 
				 to stay as structured, static HTML.
			""".trimIndent(), """
				Now the HTML is generated within the server using Kotlin, Ktor, and Ktor-html,
				a library for generating HTMl as a DSL from Ktor. This means i can get the
				best of reusable components, without using a specialised templating engine.
			""".trimIndent(), """
				Secondly, design is not my strong suit. Especially with using CSS to style HTML.
				for this reason, I've imposed some guides for writing pages. HTML features semantic 
				tags. Things like section, article, main, and nav. These all serve specific
				purposes in a web page. So i've designed my pages to utilise these for content
				structure. sections hold articles, articles contain paragraphs, and so on.
			""".trimIndent(), """
				With the page defined using these tags, defining styles fits around the 
				semantic meaning of the tags, rather than arbitrary classes. for now, there
				is only paragraph containing articles, but there could be a class of article
				than has an image. this links a tag, with a "class" of said tag, preserving
				semantic meaning.
			""".trimIndent(), """
				Finally, This site shall have its deployments be fully automated. This is
				so i can properly learn how to make and host full kubernetes applications.
				Whilst also being an example for other projects. It won't be perfect, but
				it will be a good start to making my life easier.
			""".trimIndent()

		)
	)
}