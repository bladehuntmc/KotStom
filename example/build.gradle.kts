dependencies {
    api("io.github.jglrxavpok.hephaistos", "common", "2.5.3")
    implementation("net.minestom", "minestom-snapshots", "7320437640")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation(project(":"))
}