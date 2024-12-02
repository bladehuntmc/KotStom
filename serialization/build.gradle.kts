plugins {
    alias(libs.plugins.kotlin.serialization)
}

repositories { mavenCentral() }

dependencies {
    compileOnly(libs.minestom)
    compileOnly(libs.kotlinx.serialization.core)
    compileOnly(libs.adventure.minimessage)

    testImplementation(libs.minestom)
    testImplementation(libs.kotlinx.serialization.json)
    testImplementation(libs.bundles.test)
}

tasks.test { useJUnitPlatform() }

java { withSourcesJar() }

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            artifactId = "kotstom-serialization"
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
