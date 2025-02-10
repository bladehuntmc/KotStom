import org.jreleaser.model.Active

plugins {
    id("buildlogic.common")
    `maven-publish`
    id("org.jreleaser")
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

        repositories {
            maven {
                url =
                    rootProject.projectDir
                        .resolve("build/staging-deploy")
                        .toURI()
            }
        }
    }
}
jreleaser {
    signing {
        active = Active.ALWAYS
        armored = true
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active = Active.ALWAYS
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository("build/staging-deploy")
                }
            }
        }
    }
}
