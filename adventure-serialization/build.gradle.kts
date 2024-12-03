plugins {
    alias(libs.plugins.kotlin.serialization)
}

repositories { mavenCentral() }

dependencies {
    compileOnly(libs.minestom)
    api(libs.kotlinx.serialization.core)

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
