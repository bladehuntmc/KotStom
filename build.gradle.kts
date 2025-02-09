plugins {
    id("buildlogic.common")
    `maven-publish`
    id("io.github.gradle-nexus.publish-plugin")
}

dependencies {
    compileOnly(libs.minestom)
    compileOnly(libs.adventure.minimessage)
    compileOnly(libs.kotlinx.coroutines)

    testImplementation(libs.minestom)
    testImplementation(libs.adventure.minimessage)
    testImplementation(libs.bundles.test)
}

tasks.withType<Test> { useJUnitPlatform() }

java { withSourcesJar() }

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
            artifactId = "kotstom"

            pom {
                name = "KotStom"
                description = "Kotlin extensions for Minestom"
                url = "https://github.com/bladehuntmc/KotStom"

                licenses {
                    license {
                        name = "MIT License"
                        url = "http://www.opensource.org/licenses/mit-license.php"
                    }
                }

                developers {
                    developer {
                        id = "oglass"
                        name = "oglass"
                        email = "him@oglass.dev"
                    }
                }

                issueManagement {
                    system = "GitHub"
                    url = "https://github.com/bladehuntmc/KotStom/issues"
                }

                scm {
                    connection = "scm:git:git:github.com/bladehuntmc/KotStom.git"
                    developerConnection = "scm:git:https://github.com/bladehuntmc/KotStom.git"
                    url = "https://github.com/bladehuntmc/KotStom"
                }
            }
        }
    }
}

nexusPublishing {
    repositories {
        sonatype {
            username = System.getenv("SONATYPE_USERNAME")
            password = System.getenv("SONATYPE_PASSWORD")

            nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
            snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
        }
    }
}
