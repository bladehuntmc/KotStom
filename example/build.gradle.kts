dependencies {
    implementation("net.minestom", "minestom-snapshots", property("minestom.version") as String)

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation(project(":"))
}