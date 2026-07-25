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
