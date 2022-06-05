plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(project(":clark:domain"))
    implementation(project(":sample:use-cases"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
}