package net.michael_bailey.kotlinx.html

interface SectionContainer {
	fun basicSection(block: SectionContent.() -> Unit)
}