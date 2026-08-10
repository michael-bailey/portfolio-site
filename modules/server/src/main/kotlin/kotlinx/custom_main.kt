package net.michael_bailey.kotlinx

import kotlinx.html.BODY
import kotlinx.html.emptyMap
import kotlinx.html.visit
import net.michael_bailey.kotlinx.html.CustomMain
import net.michael_bailey.kotlinx.html.Main

inline fun BODY.main(
	crossinline block: Main.() -> Unit = {}
): Unit {
	CustomMain(emptyMap, consumer).visit(block)
}