package org.radarbase.gradle.plugin

import com.github.benmanes.gradle.versions.VersionsPlugin
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create

fun Project.radarDependencyManagement(block: RadarDependencyManagementExtension.() -> Unit) {
    configure(block)
}

interface RadarDependencyManagementExtension {
    val regex: Property<String>
    val rejectMajorVersionUpdates: Property<Boolean>
}

class RadarDependencyManagementPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        val extension = extensions.create<RadarDependencyManagementExtension>("radarDependencies").apply {
            regex.convention("(^[0-9,.v-]+(-r)?|RELEASE|FINAL|GA|-CE)$")
            rejectMajorVersionUpdates.convention(false)
        }

        apply<VersionsPlugin>()

        tasks.withType(DependencyUpdatesTask::class.java) {
            doFirst {
                allprojects {
                    repositories.removeAll {
                        it is MavenArtifactRepository &&
                                it.url.toString().contains("snapshot", ignoreCase = true)
                    }
                }
            }
            val isStable = extension.regex.get().toRegex(RegexOption.IGNORE_CASE)
            val rejectMajorVersionUpdates = extension.rejectMajorVersionUpdates.get()
            rejectVersionIf {
                (!rejectMajorVersionUpdates || candidate.version.split('.', limit = 2)[0] != currentVersion.split('.', limit = 2)[0])
                        && !isStable.containsMatchIn(candidate.version)
            }
        }
    }
}
