package net.michael_bailey.processors.configuration

data class ControllerConfiguration(
	val basePath: String,
	val qualifiedName: String,
	val routes: List<RouteConfiguration>
) {

	val definedRoutes: Set<String> get() {
		basePath.split("/").forEach {}
		return setOf()
	}

}
