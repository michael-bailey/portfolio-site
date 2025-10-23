plugins {
	kotlin("jvm") version "2.2.0"

	kotlin("plugin.serialization") version "2.2.0"
}

group = "net.michael_bailey"
version = "unspecified"

repositories {
	mavenCentral()
}

dependencies {

	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")

	testImplementation(kotlin("test"))
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(21)
}