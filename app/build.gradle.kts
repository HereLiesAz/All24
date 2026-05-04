import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
}

val localProperties = Properties()
val localPropertiesFile = project.rootProject.file("local.properties")
if (localPropertiesFile.exists()) {
    FileInputStream(localPropertiesFile).use {
        localProperties.load(it)
    }
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file(localProperties.getProperty("KEYSTORE_FILE") ?: "../debug.keystore")
            storePassword = localProperties.getProperty("KEYSTORE_PASSWORD") ?: "android"
            keyAlias = localProperties.getProperty("KEY_ALIAS") ?: "androiddebugkey"
            keyPassword = localProperties.getProperty("KEY_PASSWORD") ?: "android"
        }
        create("release") {
            storeFile = file(localProperties.getProperty("KEYSTORE_FILE")?.takeIf { it.isNotBlank() } ?: "../debug.keystore")
            storePassword = localProperties.getProperty("KEYSTORE_PASSWORD")?.takeIf { it.isNotBlank() } ?: "android"
            keyAlias = localProperties.getProperty("KEY_ALIAS")?.takeIf { it.isNotBlank() } ?: "androiddebugkey"
            keyPassword = localProperties.getProperty("KEY_PASSWORD")?.takeIf { it.isNotBlank() } ?: "android"
        }
    }
    namespace = "com.hereliesaz.all24"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.hereliesaz.all24"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField(
            "String",
            "SHEETS_API_KEY",
            "\"${localProperties.getProperty("SHEETS_API_KEY")}\""
        )
        buildConfigField(
            "String",
            "SPREADSHEET_ID",
            "\"${localProperties.getProperty("SPREADSHEET_ID")}\""
        )
        buildConfigField(
            "String",
            "APPS_SCRIPT_URL",
            "\"${localProperties.getProperty("APPS_SCRIPT_URL")}\""
        )
        manifestPlaceholders["MAPS_API_KEY"] = localProperties.getProperty("MAPS_API_KEY")


    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            buildConfigField(
                "String",
                "SHEETS_API_KEY",
                "\"${localProperties.getProperty("SHEETS_API_KEY")}\""
            )
            buildConfigField(
                "String",
                "SPREADSHEET_ID",
                "\"${localProperties.getProperty("SPREADSHEET_ID")}\""
            )
            buildConfigField(
                "String",
                "APPS_SCRIPT_URL",
                "\"${localProperties.getProperty("APPS_SCRIPT_URL")}\""
            )

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1,DEPENDENCIES}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.material)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.materialIconsExtended)
    implementation(libs.coil.compose)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.google.play.services.auth)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)

    implementation(libs.google.api.client.android)
    implementation(libs.google.sheets.api)

    // --- NEW: Google Sign-In (OAuth) Dependency ---
    implementation(libs.google.play.services.auth)

    implementation(libs.androidx.navigation.compose) // Correct navigation dependency
    implementation(libs.materialIconsExtended)
    implementation(libs.material) // Assuming libs.material = "com.google.android.material:material:1.12.0"
    implementation(libs.androidx.core.ktx)
    implementation(libs.maps.compose)

    implementation(libs.gson)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

}
