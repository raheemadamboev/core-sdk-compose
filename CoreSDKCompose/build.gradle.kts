plugins {
    alias(libs.plugins.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.compose)
    id("maven-publish")
}

android {
    namespace = "xyz.teamgravity.coresdkcompose"
    compileSdk = libs.versions.sdk.compile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.sdk.min.get().toInt()
    }

    lint {
        targetSdk = libs.versions.sdk.target.get().toInt()
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvm.target.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    // compose
    implementation(platform(libs.compose))
    implementation(libs.compose.ui)
    implementation(libs.compose.graphics)
    implementation(libs.compose.preview)
    implementation(libs.compose.material3)

    // compose paging
    implementation(libs.compose.paging)

    // gravity core
    implementation(libs.gravity.core)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.raheemadamboev"
            artifactId = "core-sdk-compose"
            version = "1.0.20"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}