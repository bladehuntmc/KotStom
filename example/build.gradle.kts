plugins { kotlin("plugin.serialization") version "2.0.0" }

dependencies {
    implementation(libs.minestom)
    implementation(libs.adventure.minimessage)

    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.serialization.json)

    implementation(project(":"))
    implementation(project(":adventure-serialization"))
}
