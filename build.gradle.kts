import org.jetbrains.intellij.tasks.PublishTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// https://kotlinlang.org/docs/reference/using-gradle.html

plugins {
    `kotlin-dsl`
    idea
    id("org.jetbrains.intellij") version "0.4.8" // https://github.com/JetBrains/gradle-intellij-plugin
    id ("org.jetbrains.kotlin.jvm") version "1.3.31"
}

intellij {
    pluginName = "Groovyfier"
    version = "191.6183.87" // overrides plugin.xml since-build in case of conflict, https://www.jetbrains.com/intellij-repository/releases
    setPlugins("Groovy") // Bundled plugin dependencies
}

tasks.withType<PublishTask> {
    username(project.property("intellijPublishUsername"))
    password(project.property("intellijPublishPassword"))
}

group = "com.github.masooh.intellij.plugin.groovyfier"
version = "0.1" // overrides plugin.xml version in case of conflict

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-jdk7"))
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}