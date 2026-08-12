package net.michael_bailey.kotlinx.css

import kotlinx.css.StyledElement
import kotlin.reflect.KProperty

/**
 * This is a direct copy of [kotlinx.css.CssProperty], because it's missing a key grid part
 * If an alternative is found, then that will replace this eventually.
 */
internal class MissingCssProperty<T>(private val default: (() -> T)? = null) {
	operator fun getValue(thisRef: StyledElement, property: KProperty<*>): T {
		default?.let { default ->
			if (!thisRef.declarations.containsKey(property.name)) {
				thisRef.declarations[property.name] = default() as Any
			}
		}

		@Suppress("UNCHECKED_CAST")
		return thisRef.declarations[property.name] as T
	}

	operator fun setValue(
		thisRef: StyledElement,
		property: KProperty<*>,
		value: T
	) {
		thisRef.declarations[property.name] = value as Any
	}
}