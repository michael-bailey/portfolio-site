package net.michael_bailey.home.repository

import net.michael_bailey.home.model.ContentArticle
import net.michael_bailey.home.model.ContentSection
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
				getGymLogBookArticle(),
				getBramblePiArticle()
			)
		),
	)

	private fun getGymLogBookArticle() = ContentArticle(
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

	private fun getBramblePiArticle() = ContentArticle(
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
}