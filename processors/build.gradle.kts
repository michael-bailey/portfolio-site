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
//	api(kotlin("reflect"))

	implementation(project(":metadata"))

	implementation("com.google.devtools.ksp:symbol-processing-api:2.2.0-1.0.29")

	implementation("io.ktor:ktor-server-core-jvm:3.2.3")

	implementation("com.squareup:kotlinpoet:1.15.0")
	implementation("com.squareup:kotlinpoet-ksp:1.15.0")

	testImplementation(kotlin("test"))
	testImplementation("org.mockito.kotlin:mockito-kotlin:6.0.0")
	testImplementation("com.github.tschuchortdev:kotlin-compile-testing:1.6.0")
	testImplementation("com.github.tschuchortdev:kotlin-compile-testing-ksp:1.6.0")
}

kotlin {
	jvmToolchain(21)
}