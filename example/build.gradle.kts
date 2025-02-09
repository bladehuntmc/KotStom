plugins {
    id("buildlogic.common")
}

dependencies {
    implementation(libs.minestom)
    implementation(libs.adventure.minimessage)

    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.serialization.json)

    implementation(project(":"))
    implementation(project(":adventure-serialization"))
}
