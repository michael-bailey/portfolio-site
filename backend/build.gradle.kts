
plugins {
	kotlin("jvm") version "2.2.0"
	kotlin("plugin.serialization") version "2.2.0"
	kotlin("plugin.spring") version "2.2.0"

	id("com.google.devtools.ksp") version "2.2.0-2.0.2"

	id("org.springframework.boot") version "3.4.5"
	id("io.spring.dependency-management") version "1.1.7"
	id("com.netflix.dgs.codegen") version "7.0.3"
	id("com.apollographql.apollo") version "4.1.0"
	id("jacoco")

	id("environment.file")
}

group = "io.github.michael_bailey"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

repositories {
	mavenCentral()
}

dependencies {

	implementation(kotlin("reflect"))

	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")

	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.10.2")

	implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.0")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")

	implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:8.0.0"))
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")

	implementation("com.apollographql.apollo:apollo-runtime")

	implementation("org.commonmark:commonmark:0.21.0")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	implementation("org.jetbrains.kotlin:kotlin-reflect")

	runtimeOnly("org.postgresql:postgresql")

	ksp(project(":processors"))

	implementation(project(":metadata"))

	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
	implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:3.1.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.graphql:spring-graphql-test")
	testImplementation("org.springframework.security:spring-security-test")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.generateJava {
	schemaPaths.add("${projectDir}/src/main/resources/schema")
	packageName = "io.github.michael_bailey.spring_blog.graphql"
	generateClient = true
	generateInterfaces = true
}

apollo {
	service("github") {
		packageName.set("com.github.client")
		introspection {
			endpointUrl.set("https://api.github.com/graphql")
			schemaFile.set(file("src/main/graphql/github.graphqls"))
		}
	}
}

tasks.test {
	useJUnitPlatform()
}

jacoco {
	toolVersion = "0.8.11" // recent stable version with Kotlin support
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
	reports {
		xml.required.set(true)
		html.required.set(true)
	}
}

envFile {
	includeRootEnvFile = false
}