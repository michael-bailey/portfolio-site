plugins {
	kotlin("jvm") version "2.2.0"
}

group = "net.michael_bailey"
version = "unspecified"

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib"))
	api(kotlin("reflect"))

	implementation(project(":metadata"))

	implementation("com.google.devtools.ksp:symbol-processing-api:1.9.10-1.0.13")

	implementation("com.squareup:kotlinpoet:1.15.0")
	implementation("com.squareup:kotlinpoet-ksp:1.15.0")
}

kotlin {
	jvmToolchain(21)
}