import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
	id("org.jetbrains.kotlin.jvm") version "1.8.21"
	id("org.jetbrains.kotlin.plugin.spring") version "1.8.21"
}

group = "com.ssi.schaefer.lunchbon"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	google()

	maven {
		url = uri("https://firebase.google.com/docs/android/setup#available-libraries")
		name = "Firebase"
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.apache.commons:commons-text:1.9")

	// Firebase
	implementation("com.google.firebase:firebase-admin:9.1.1")
	implementation("com.google.cloud:google-cloud-firestore:1.22.0")
	implementation("com.google.firebase:firebase-auth:21.0.1")
	implementation("com.google.firebase:firebase-bom:31.0.0")
	implementation("com.google.firebase:firebase-auth-ktx:22.3.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

val integrationTest = tasks.register<Test>("integrationTest") {
	useJUnitPlatform {
		includeTags("integration")
		excludeTags("*")
	}
}

val unitTest = tasks.named<Test>("test") {
	useJUnitPlatform {
		excludeTags("integration")
	}
}

tasks.register("runUnitTests") {
	dependsOn(unitTest)
}

tasks.register("runIntegrationTests") {
	dependsOn(integrationTest)
}
