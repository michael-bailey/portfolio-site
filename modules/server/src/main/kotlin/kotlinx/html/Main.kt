package net.michael_bailey.kotlinx.html

interface Main {
	fun section(header: String, block: Section.() -> Unit)
}