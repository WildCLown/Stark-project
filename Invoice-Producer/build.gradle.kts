plugins {
    kotlin("jvm") version "2.0.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.starkbank:sdk:2.19.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("org.example.com.stark.invoice.MainKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "org.example.com.stark.invoice.MainKt"
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })
    archiveFileName.set("app.jar")
}

java {
    sourceCompatibility = JavaVersion.VERSION_22
    targetCompatibility = JavaVersion.VERSION_22
}