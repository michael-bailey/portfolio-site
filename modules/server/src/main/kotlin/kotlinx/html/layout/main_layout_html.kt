package net.michael_bailey.kotlinx.html.layout

import io.ktor.http.*
import kotlinx.css.*
import kotlinx.html.*
import net.michael_bailey.kotlinx.css.MissingCssProperty
import net.michael_bailey.kotlinx.css.gridGap
import net.michael_bailey.kotlinx.css.textIndent
import net.michael_bailey.kotlinx.html.SectionContainer
import net.michael_bailey.kotlinx.main
import net.michael_bailey.kotlinx.nav

inline fun HTML.mainLayout(crossinline block: SectionContainer.() -> Unit) {
	body {
		header {
			h1 {
				+ "Michael Bailey .Net"
			}
		}
		nav {
			addLink("/", "Home")
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
		meta(name = "viewport", content="width=device-width, initial-scale=1.0") {  }
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
		width = min(SCREEN_WIDTH, LinearDimension("900px"))
		minHeight = SCREEN_HEIGHT
		margin = Margin(LinearDimension.auto)

		gridGap = 1.rem

		gridTemplateRows = GridTemplateRows(
			LinearDimension("5rem"),
			LinearDimension("3rem"),
			LinearDimension.auto,
			LinearDimension("5rem"),
		)

		gridTemplateAreas = GridTemplateAreas(
			"\"$HEADER_AREA\" \"$NAV_AREA\" \"$CONTENT_AREA\" \"$FOOTER_AREA\""
		)
	}

	header {
		padding = Padding(LINEAR_ZERO, LinearDimension("1.5rem"))
		gridArea = HEADER_AREA
		alignContent = Align.center
	}

	nav {

		display = Display.flex

		gridArea = NAV_AREA

		child("a") {
			display = Display.block

			width = LinearDimension("100%")
			height = LinearDimension("100%")

			textAlign = TextAlign.center
			alignContent = Align.center

			borderColor = Color.lightGray
			borderStyle = BorderStyle.solid

			borderWidth = LinearDimension("1px 0")
			borderLeftWidth = LinearDimension("1px")

			lastChild {
				borderTopRightRadius = 10.px
				borderBottomRightRadius = 10.px
				borderRightWidth = 1.px
				marginRight = 1.rem
			}

			firstChild {
				borderTopLeftRadius = 10.px
				borderBottomLeftRadius = 10.px
				marginLeft = 1.rem
			}
		}
	}

	main {
		gridArea = CONTENT_AREA
	}

	h3 {
		marginBottom = LinearDimension("0.3rem")
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

				margin = Margin(LinearDimension("1ch"), LinearDimension("0"));
				textIndent = LinearDimension("2ch");
			}
		}
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
val NAV_AREA = "NAV"
val FOOTER_AREA = "Footer"

