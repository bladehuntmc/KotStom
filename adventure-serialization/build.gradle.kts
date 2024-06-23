plugins { kotlin("plugin.serialization") version "2.0.0" }

repositories { mavenCentral() }

dependencies {
    implementation("net.kyori:adventure-nbt:4.17.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.7.0")

    testImplementation("net.minestom", "minestom-snapshots", property("minestom.version") as String)
    testImplementation("io.kotest:kotest-assertions-core:5.9.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.9.0")
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
            url = uri("https://gitlab.com/api/v4/projects/56581102/packages/maven")
            credentials(HttpHeaderCredentials::class) {
                name = "Job-Token"
                value = properties["CI_JOB_TOKEN"] as String?
            }
            authentication { create("header", HttpHeaderAuthentication::class) }
        }
    }
}
