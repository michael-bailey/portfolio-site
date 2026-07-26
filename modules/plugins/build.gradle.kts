plugins {
	`java-gradle-plugin`
	kotlin("jvm") version "1.9.25"
}

group = "io.github.michael_bailey"
version = "unspecified"

repositories {
	mavenCentral()
}

dependencies {

}

kotlin {
	jvmToolchain(21)
}

gradlePlugin {
	val environmentFile by plugins.creating {
		id = "environment.file"
		implementationClass = "plugin.EnvironmentFilePlugin"
	}

	val javascriptBuild by plugins.creating {
		id = "javascript"
		implementationClass = "plugin.JavascriptProject"
	}

	val versionManagerPlugin by plugins.creating {
		id = "version.management"
		implementationClass = "plugin.VersionManagerPlugin"
	}
}