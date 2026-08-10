package net.michael_bailey.kotlinx.html.layout

import io.ktor.http.*
import kotlinx.css.*
import kotlinx.html.*
import net.michael_bailey.kotlinx.css.MissingCssProperty
import net.michael_bailey.kotlinx.html.Main
import net.michael_bailey.kotlinx.main

inline fun HTML.mainLayout(crossinline block: Main.() -> Unit) {
	body {
		header {
			h1 {
				+ "Michael Bailey .Net"
			}
		}
		main {
			block()
		}
		footer {
			span {
				+ "(C) Michael Bailey – 2026"
			}
		}
	}
}

inline fun HTML.mainHead() {
	head {
		link(href = "index.css", rel = LinkHeader.Rel.Stylesheet)
	}
}

fun CssBuilder.applyMainLayout() {
	"*" {
		padding = Padding(LINEAR_ZERO)
		margin = Margin(LINEAR_ZERO)
		backgroundColor = Color.white
		color = Color.black

		boxSizing = BoxSizing.borderBox
		fontFamily = "'Courier New', Courier, monospace"
	}

	html {
		width = SCREEN_WIDTH
	}

	body {
		display = Display.grid

		width = SCREEN_WIDTH
		minHeight = SCREEN_HEIGHT

		gridTemplateRows = GridTemplateRows(
			LinearDimension("5rem"),
			LinearDimension.auto,
			LinearDimension("5rem"),
		)

		gridTemplateAreas = GridTemplateAreas(
			"\"$HEADER_AREA\" \"$CONTENT_AREA\" \"$FOOTER_AREA\""
		)
	}

	header {
		padding = Padding(LINEAR_ZERO, LinearDimension("2rem"))
		gridArea = HEADER_AREA
		alignContent = Align.center
	}

	main {
		gridArea = CONTENT_AREA
	}

	section {
		margin = Margin(LinearDimension("1rem"))

		child("*") {
			firstChild {
				borderTopWidth = BORDER_WIDTH
				borderRadius = LinearDimension("10px 10px 0 0")
			}

			lastChild {
				borderTopWidth = BORDER_WIDTH
				borderRadius = LinearDimension("0 0 10px 10px")
			}

			padding = Padding(LinearDimension("1rem"))
			borderWidth = LinearDimension("0 1px")
			borderColor = Color.lightGray
			borderStyle = BorderStyle.solid
			borderBottomWidth = LinearDimension("1px")
		}
	}

	section {
		child("article") {
			child("p") {

			}
		}
	}

	a {
//		this.

	}


	article {}

	a {

	}

	footer {
		height = LinearDimension("100%")

		gridArea = "Footer"

		justifyContent = JustifyContent.center
		textAlign = TextAlign.center
		verticalAlign = VerticalAlign.middle
		alignContent = Align.center
	}

}

var StyledElement.gridArea: String by MissingCssProperty()

val SCREEN_WIDTH = LinearDimension("100vw")
val SCREEN_HEIGHT = LinearDimension("100vh")
val LINEAR_ZERO = LinearDimension("0")
val BORDER_WIDTH = LinearDimension("1px")

val HEADER_AREA = "Header"
val CONTENT_AREA = "Content"
val FOOTER_AREA = "Footer"

