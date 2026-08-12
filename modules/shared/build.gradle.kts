plugins {
	kotlin("multiplatform") version "2.4.10"
}

version = property("projectVersion") as String

repositories {
	mavenCentral()
}

kotlin {
	jvm {
	}
}