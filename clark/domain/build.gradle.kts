plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
    id("com.palantir.git-version")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.somekoder.clark.domain"
            artifactId = "clark-domain"
            val gitVersion: groovy.lang.Closure<String> by extra
            version = gitVersion()

            from(components["java"])
        }
    }
}