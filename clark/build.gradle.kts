plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("com.palantir.git-version") version("0.15.0")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    testFixtures {
        enable = true
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    api(project(":clark:domain"))
    api(project(":clark:viewmodel"))
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.somekoder.clark"
            artifactId = "clark"
            val gitVersion: groovy.lang.Closure<String> by extra
            version = gitVersion()

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}