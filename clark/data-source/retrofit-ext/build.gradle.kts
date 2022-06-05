plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
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
    implementation(project(":clark:domain"))

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.somekoder.clark.data_source.retrofit_ext"
            artifactId = "clark-retrofit-ext"
            version = Config.version

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}