package net.michael_bailey.kotlinx

import kotlinx.html.BODY
import kotlinx.html.emptyMap
import net.michael_bailey.components.Navigation
import net.michael_bailey.components.NavigationContainer
import net.michael_bailey.kotlinx.html.Main
import net.michael_bailey.kotlinx.html.SectionContainer

inline fun BODY.main(
	crossinline block: SectionContainer.() -> Unit = {}
): Unit {
	Main(emptyMap, consumer)
		.apply(block)
		.render()
}

inline fun BODY.nav(
	crossinline block: NavigationContainer.() -> Unit = {},
) {
	Navigation(this.consumer).apply(block).render()
}