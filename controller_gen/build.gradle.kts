plugins {
	kotlin("jvm") version "2.2.0"
	kotlin("plugin.serialization") version "2.2.0"
	idea
}

group = "net.michael_bailey"
version = "0.0.1"

kotlin {
	jvmToolchain(21)
}

repositories {
	mavenCentral()
}

dependencies {

	implementation(kotlin("stdlib"))

	implementation(project(":BITLIB"))

	implementation("io.ktor:ktor-server-core-jvm:3.2.3")


	implementation(kotlin("serialization"))
	api("io.ktor:ktor-server-content-negotiation:3.2.3")
	api("io.ktor:ktor-server-auth:3.2.3")
	api("io.ktor:ktor-serialization-kotlinx-json:3.2.3")
	api("io.ktor:ktor-serialization-kotlinx-xml:3.2.3")
	api("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")

	implementation(platform("io.insert-koin:koin-bom:4.1.0"))
	implementation("io.insert-koin:koin-core")
	implementation("io.insert-koin:koin-ktor")


	implementation("com.google.devtools.ksp:symbol-processing-api:2.2.0-2.0.2")
	implementation("com.squareup:kotlinpoet:1.15.0")
	implementation("com.squareup:kotlinpoet-ksp:1.15.0")

	testImplementation(kotlin("test"))
	testImplementation("org.mockito.kotlin:mockito-kotlin:6.0.0")
}