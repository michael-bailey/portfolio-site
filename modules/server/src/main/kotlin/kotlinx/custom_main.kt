package net.michael_bailey.kotlinx

import kotlinx.html.BODY
import kotlinx.html.emptyMap
import kotlinx.html.visit
import net.michael_bailey.kotlinx.html.Main
import net.michael_bailey.kotlinx.html.SectionContainer

inline fun BODY.main(
	crossinline block: SectionContainer.() -> Unit = {}
): Unit {
	Main(emptyMap, consumer).visit(block)
}