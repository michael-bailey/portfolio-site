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

dependencyResolutionManagement {
	versionCatalogs {
		create("buildLibs") {
			this.from(files("gradle/buildLibs.versions.toml"))
		}
	}
}

includeBuild("modules/plugins")

include("backend")
include("frontend")

includeBuild("plugins")