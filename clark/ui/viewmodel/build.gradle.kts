plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.somekoder.clark.viewmodel"

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
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")
    val lifecycleVersion = "2.5.0-rc01"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.somekoder.clark.ui.viewmodel"
            artifactId = "clark-viewmodel"
            version = Config.version

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}