import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "7.1.2"

    `maven-publish`

    id("org.jetbrains.dokka") version "1.9.20"
}

repositories {
    mavenCentral()

    maven("https://jitpack.io")
    maven("https://repo.spongepowered.org/maven")
    maven("https://repo.velocitypowered.com/snapshots/")
}


dependencies {
    compileOnly("io.github.jglrxavpok.hephaistos", "common", "2.5.3")
    compileOnly("net.minestom", "minestom-snapshots", "7320437640")
    api("net.kyori:adventure-text-minimessage:4.16.0")

    compileOnly("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    testImplementation("io.kotest:kotest-assertions-core:5.8.1")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.1")
}

group = "net.bladehunt.kotstom"
version = "0.1.0-beta"

kotlin {
    jvmToolchain(17)
    compilerOptions {
        freeCompilerArgs.addAll("-Xinline-classes", "-Xopt-in=kotlin.RequiresOptIn", "-Xcontext-receivers")
    }
}

configurations {
    testImplementation {
        extendsFrom(configurations.compileOnly.get())
    }
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName = "kotstom"
        mergeServiceFiles()
        minimize()
    }

    withType<Test> {
        useJUnitPlatform()
    }

    build {
        dependsOn(shadowJar)
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            artifactId = "kotstom"
        }
    }
    repositories {
        maven {
            url = uri("https://gitlab.com/api/v4/projects/56581102/packages/maven")
            credentials(HttpHeaderCredentials::class) {
                name = "Job-Token"
                value = properties["CI_JOB_TOKEN"] as String?
            }
            authentication {
                create("header", HttpHeaderAuthentication::class)
            }
        }
    }
}