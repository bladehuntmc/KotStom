import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.dokka)

    `maven-publish`
}

allprojects {
    group = property("group") ?: "net.bladehunt"
    version = property("version") ?: "0.1.0"

    repositories {
        mavenCentral()

        maven("https://jitpack.io")
        maven("https://repo.spongepowered.org/maven")
        maven("https://repo.velocitypowered.com/snapshots/")
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
            compilerOptions { freeCompilerArgs.addAll("-Xinline-classes", "-Xcontext-receivers") }
        }
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "maven-publish")
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
