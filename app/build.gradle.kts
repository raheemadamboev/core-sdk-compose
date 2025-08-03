import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.compose)
}

android {
    namespace = "xyz.teamgravity.coresdkdemoapp"
    compileSdk = libs.versions.sdk.compile.get().toInt()

    defaultConfig {
        applicationId = "xyz.teamgravity.coresdkdemoapp"
        minSdk = libs.versions.sdk.min.get().toInt()
        targetSdk = libs.versions.sdk.target.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    lint {
        disable.addAll(
            listOf(
                "NotificationPermission"
            )
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        target {
            compilerOptions {
                jvmTarget = JvmTarget.JVM_17
            }
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {

    // gravity core
    implementation(libs.gravity.core)
    implementation(projects.coreSDKCompose)
//    implementation("com.github.raheemadamboev:core-sdk-compose:1.0.27")

    // compose
    implementation(platform(libs.compose))
    implementation(libs.compose.ui)
    implementation(libs.compose.graphics)
    implementation(libs.compose.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.icons)

    // compose activity
    implementation(libs.compose.activity)

    // compose lifecycle
    implementation(libs.compose.lifecycle)

    // core
    implementation(libs.core)

    // timber
    implementation(libs.timber)
}