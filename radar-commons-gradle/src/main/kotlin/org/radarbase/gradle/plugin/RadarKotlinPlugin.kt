package org.radarbase.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ApplicationPlugin
import org.gradle.api.plugins.JavaApplication
import org.gradle.api.provider.Property
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin

fun Project.radarKotlin(configure: RadarKotlinExtension.() -> Unit) {
    configure(configure)
}

interface RadarKotlinExtension {
    val javaVersion: Property<Int>
    val kotlinVersion: Property<String>
    val junitVersion: Property<String>
    val log4j2Version: Property<String>
    val slf4jVersion: Property<String>
    val ktlintVersion: Property<String>
}

class RadarKotlinPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        val extension = extensions.create<RadarKotlinExtension>("radarKotlin").apply {
            javaVersion.convention(Versions.java)
            kotlinVersion.convention(Versions.kotlin)
            junitVersion.convention(Versions.junit)
            ktlintVersion.convention(Versions.ktlint)
            slf4jVersion.convention(Versions.ktlint)
        }

        apply(plugin = "kotlin")
        apply<KtlintPlugin>()

        repositories {
            mavenCentral() {
                mavenContent {
                    releasesOnly()
                }
            }
            mavenLocal()
            maven(url = "https://packages.confluent.io/maven/") {
                mavenContent {
                    releasesOnly()
                }
            }
            maven(url = "https://oss.sonatype.org/content/repositories/snapshots") {
                mavenContent {
                    snapshotsOnly()
                }
            }
        }

        tasks.withType<JavaCompile> {
            options.release.set(extension.javaVersion)
        }

        tasks.withType<KotlinCompile> {
            compilerOptions {
                jvmTarget.set(extension.javaVersion.map { JvmTarget.fromTarget(it.toString()) })
                val kotlinVersion = extension.kotlinVersion.map { version ->
                    KotlinVersion.fromVersion(
                        version
                            .splitToSequence('.')
                            .take(2)
                            .joinToString(separator = "."),
                    )
                }
                apiVersion.set(kotlinVersion)
                languageVersion.set(kotlinVersion)
            }
        }

        extensions.configure<KtlintExtension> {
            version.set(extension.ktlintVersion)
        }

        dependencies {
            configurations["testImplementation"](extension.junitVersion.map { "org.junit.jupiter:junit-jupiter-api:$it" })
            configurations["testRuntimeOnly"](extension.junitVersion.map { "org.junit.jupiter:junit-jupiter-engine:$it" })
        }

        tasks.withType<Test> {
            testLogging {
                events("passed", "skipped", "failed")
                showStandardStreams = true
                exceptionFormat = TestExceptionFormat.FULL
            }
            useJUnitPlatform()
        }

        afterEvaluate {
            if (extension.slf4jVersion.isPresent) {
                dependencies {
                    val implementation by configurations
                    implementation("org.slf4j:slf4j-api:${extension.slf4jVersion.get()}")
                }
            }
            if (extension.log4j2Version.isPresent) {
                dependencies {
                    val log4j2Version = extension.log4j2Version.get()

                    if (plugins.hasPlugin("application")) {
                        val runtimeOnly by configurations
                        runtimeOnly("org.apache.logging.log4j:log4j-slf4j2-impl:$log4j2Version")
                        runtimeOnly("org.apache.logging.log4j:log4j-core:$log4j2Version")
                        runtimeOnly("org.apache.logging.log4j:log4j-jul:$log4j2Version")
                    } else {
                        val testRuntimeOnly by configurations
                        testRuntimeOnly("org.apache.logging.log4j:log4j-slf4j2-impl:$log4j2Version")
                        testRuntimeOnly("org.apache.logging.log4j:log4j-core:$log4j2Version")
                        testRuntimeOnly("org.apache.logging.log4j:log4j-jul:$log4j2Version")
                    }
                }

                tasks.withType<Test> {
                    if ("java.util.logging.manager" !in systemProperties) {
                        systemProperty(
                            "java.util.logging.manager",
                            "org.apache.logging.log4j.jul.LogManager"
                        )
                    }
                }

                if (plugins.hasPlugin(ApplicationPlugin::class)) {
                    extensions.configure<JavaApplication> {
                        if (applicationDefaultJvmArgs.none { "-Djava.util.logging.manager=" in it }) {
                            applicationDefaultJvmArgs += "-Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager"
                        }
                    }
                }
            }
        }

        configurations.named("implementation") {
            resolutionStrategy.cacheChangingModulesFor(0, "SECONDS")
        }
    }
}
