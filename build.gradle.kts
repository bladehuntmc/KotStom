import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.23"
    id("io.github.goooler.shadow") version "8.1.7"

    `maven-publish`

    id("org.jetbrains.dokka") version "1.9.20"
}

allprojects {
    group = property("group") ?: "net.bladehunt"
    version = property("version") ?: "0.1.0"

    repositories {
        mavenCentral()

        maven("https://jitpack.io")
        maven("https://repo.spongepowered.org/maven")
        maven("https://repo.velocitypowered.com/snapshots/")
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "21"
            compilerOptions {
                freeCompilerArgs.addAll("-Xinline-classes", "-Xcontext-receivers")
            }
        }
    }
}

java {
    withSourcesJar()
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
}

dependencies {
    compileOnly("io.github.jglrxavpok.hephaistos", "common", "2.5.3")
    compileOnly("net.minestom", "minestom-snapshots", property("minestom.version") as String)
    api("net.kyori:adventure-text-minimessage:4.16.0")

    compileOnly("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
    compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    testImplementation("io.kotest:kotest-assertions-core:5.8.1")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.1")
}

tasks.named<ShadowJar>("shadowJar") {
    archiveBaseName = "kotstom"
    mergeServiceFiles()
    minimize()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.build {
    dependsOn("shadowJar")
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