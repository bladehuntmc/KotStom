plugins { kotlin("plugin.serialization") version "2.0.0" }

dependencies {
    implementation("net.minestom", "minestom-snapshots", property("minestom.version") as String)

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation(project(":"))
    implementation(project(":adventure-serialization"))
}
