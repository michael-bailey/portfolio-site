plugins {
	kotlin("jvm") version "2.2.0"
	`java-library`
}

group = "net.michael_bailey"
version = "unspecified"

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib"))


	testImplementation(kotlin("test"))
}

tasks.test {
	useJUnitPlatform()
}

kotlin {
	jvmToolchain(21)
}