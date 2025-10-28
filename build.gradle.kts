plugins {
	kotlin("jvm") version "2.2.0" apply false
	id("environment.file") apply false
	id("version.management") apply true
}

version = property("projectVersion") as String

repositories {
	mavenCentral()
}
