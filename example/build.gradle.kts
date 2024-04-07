plugins {
    kotlin("jvm")
}

group = "net.bladehunt.kstom"
version = "1.0.0"

repositories {
    mavenCentral()

    maven(url = "https://jitpack.io")
    maven(url = "https://repo.spongepowered.org/maven")
    maven(url = "https://repo.velocitypowered.com/snapshots/")
}

dependencies {
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

    // Compile Minestom into project
    api("io.github.jglrxavpok.hephaistos", "common", "2.5.3")
    implementation("net.minestom", "minestom-snapshots", "7320437640")

    // import kotlinx serialization
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    api("net.kyori:adventure-text-minimessage:4.16.0")
    api(project(":"))
}

kotlin {
    jvmToolchain(17)
}