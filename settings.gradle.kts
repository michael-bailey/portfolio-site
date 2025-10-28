rootProject.name = "spring-blog"

pluginManagement {
	repositories {
		google()
		gradlePluginPortal()
		mavenCentral()
	}
}

dependencyResolutionManagement {
	repositories {
		google()
		mavenCentral()
	}
}

includeBuild("modules/plugins")

include("backend")
include("frontend")

include(":modules:shared")
include(":modules:server")
include(":modules:client")

includeBuild("plugins")
//include("processors")
include("metadata")
include("controller_gen")
include("BITLIB")