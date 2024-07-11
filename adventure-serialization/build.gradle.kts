plugins { kotlin("plugin.serialization") version "2.0.0" }

repositories { mavenCentral() }

dependencies {
    implementation(libs.adventure.nbt)
    implementation(libs.kotlinx.serialization.core)

    testImplementation(libs.minestom)
    testImplementation(libs.bundles.test)
}

tasks.test { useJUnitPlatform() }

java { withSourcesJar() }

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            artifactId = "kotstom-adventure-serialization"
        }
    }
    repositories {
        maven {
            name = "releases"
            url = uri("https://mvn.bladehunt.net/releases")
            credentials(PasswordCredentials::class) {
                username = System.getenv("MAVEN_NAME")
                password = System.getenv("MAVEN_SECRET")
            }
            authentication { create<BasicAuthentication>("basic") }
        }
    }
}
