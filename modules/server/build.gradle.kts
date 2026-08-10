plugins {
	kotlin("jvm") version "2.4.10"
	id("io.ktor.plugin") version "3.5.1"
	id("com.google.devtools.ksp") version "2.3.2"
	id("io.insert-koin.compiler.plugin") version "1.1.0"
}

version = property("projectVersion") as String

dependencies {
	implementation("io.ktor:ktor-server-netty:3.5.1")
	implementation("io.ktor:ktor-server-openapi:3.5.1")
	implementation("io.ktor:ktor-server-routing-openapi:3.5.1")
	implementation("io.ktor:ktor-server-auth:3.5.1")
	implementation("io.ktor:ktor-server-content-negotiation:3.5.1")
	implementation("io.ktor:ktor-server-call-logging:3.5.1")
	implementation("io.ktor:ktor-server-call-id:3.5.1")
	implementation("io.ktor:ktor-server-metrics-micrometer:3.5.1")
	implementation("io.micrometer:micrometer-registry-prometheus:1.17.0")
	implementation("io.insert-koin:koin-ktor:4.2.2")
	implementation("io.insert-koin:koin-logger-slf4j:4.2.2")
	implementation("io.ktor:ktor-serialization-kotlinx-json:3.5.1")
	implementation("io.ktor:ktor-serialization-kotlinx-xml:3.5.1")
	implementation("io.insert-koin:koin-annotations:2.3.1")
	implementation("io.ktor:ktor-server-html-builder:3.5.1")
	implementation("org.jetbrains.kotlin-wrappers:kotlin-css:1.0.0-pre.625")
	ksp("io.insert-koin:koin-ksp-compiler:2.3.1")
}

kotlin {
	compilerOptions {
		optIn.add(
			"kotlin.uuid.ExperimentalUuidApi"
		)
	}
}

application {
	mainClass = "net.michael_bailey.MainKt"
}

ktor {
	openApi {
		this.enabled = true
	}

	docker {
		jreVersion = JavaVersion.VERSION_22
		this.localImageName = "michael-bailey-net"
		this.imageTag = version.toString()
	}

	fatJar {
		archiveFileName = "server-full.jar"
	}
}