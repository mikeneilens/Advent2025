plugins {
    kotlin("jvm") version "2.2.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
kotlin {
    jvmToolchain(22)
}
dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-assertions-core-jvm:6.0.4")
    testImplementation("io.kotest:kotest-assertions-json-jvm:6.0.4")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:6.0.4")
}

tasks.test {
    useJUnitPlatform()
}