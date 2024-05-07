/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java library project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.7/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the java-library plugin for API and implementation separation.
    java
}

repositories {
    mavenCentral()
}

dependencies {

	implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.0.2")
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)
	testImplementation("com.hivemq:hivemq-mqtt-client:1.3.0")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used internally, and not exposed to consumers on their own compile classpath.
    implementation(libs.guava)
	implementation("com.google.code.gson:gson:2.7")
	implementation("com.hivemq:hivemq-mqtt-client:1.3.0")
    implementation("org.apache.logging.log4j:log4j-api:2.14.1")
    implementation("org.apache.logging.log4j:log4j-core:2.14.1")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}