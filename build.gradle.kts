plugins {
	java
	id("org.springframework.boot") version "3.3.0"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "moe.lita"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.mockito:mockito-core:5.12.0")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	compileOnly("org.projectlombok:lombok:1.18.32")
	annotationProcessor("org.projectlombok:lombok:1.18.32")
	testCompileOnly("org.projectlombok:lombok:1.18.32")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.32")

	implementation("com.discord4j:discord4j-core:3.2.6")
	implementation("org.xerial:sqlite-jdbc:3.45.3.0")
	implementation("org.hibernate.orm:hibernate-community-dialects:6.5.2.Final")
	implementation("com.google.guava:guava:33.2.0-jre")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
