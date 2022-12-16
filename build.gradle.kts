import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.7.20"
	id("org.springframework.boot") version "2.5.7"
	id("io.spring.dependency-management") version "1.1.0"
	id("org.jetbrains.kotlin.plugin.spring") version "1.5.10"
	id("org.jetbrains.kotlin.plugin.allopen") version "1.5.10"
}

group = "com.chacal.spring"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()

	maven {
		url = uri("https://oss.sonatype.org/content/repositories/snapshots")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springframework.boot:spring-boot-starter-test")
	implementation("io.projectreactor:reactor-test")
	implementation("com.h2database:h2")
	implementation("com.trendyol:kediatr-core:2.0.0-SNAPSHOT")
	//implementation("com.trendyol:kediatr-spring-starter:2.0.0-SNAPSHOT")
}

allOpen {
	annotations("javax.persistence.Entity", "javax.persistence.MappedSuperclass", "javax.persistence.Embedabble")
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "11"
}

tasks.test {
	useJUnitPlatform()
}
