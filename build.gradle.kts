plugins {
	id("environment.file") apply false
	id("version.management") apply true
}

version = property("projectVersion") as String

repositories {
	mavenCentral()
}
