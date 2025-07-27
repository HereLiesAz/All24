import java.io.FileInputStream
import java.util.Properties

val localProperties = Properties().apply {
    val localPropertiesFile = project.rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        load(FileInputStream(localPropertiesFile))
    } else {
        // Optional: Log a warning or throw an error if local.properties is missing
        println("Warning: local.properties file not found at ${localPropertiesFile.absolutePath}")
    }
}

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.composeCompiler)
}

android {
    signingConfigs {
        getByName("debug") {
            // Read the keystore configuration securely from local.properties
            storeFile = file(localProperties.getProperty("KEYSTORE_FILE"))
            storePassword = localProperties.getProperty("KEYSTORE_PASSWORD")
            keyAlias = localProperties.getProperty("KEY_ALIAS")
            keyPassword = localProperties.getProperty("KEY_PASSWORD")
        }
    }
    namespace = "com.hereliesaz.all24"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.hereliesaz.all24"
        minSdk = 26
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


    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

    packaging {
        resources {
            resources.excludes += "META-INF/INDEX.LIST"
            excludes.add("META-INF/DEPENDENCIES")
            excludes.add("META-INF/AL2.0")
            excludes.add("META-INF/LGPL2.1")
        }
    }
    buildToolsVersion = "36.0.0"
    ndkVersion = "29.0.13599879 rc2"
}
composeCompiler {
    reportsDestination = layout.buildDirectory.dir("compose_compiler")
}
dependencies {

    // Core Android & Jetpack
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Jetpack Compose - M3 Expressive Design
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Firebase - Bill of Materials (BoM)
    implementation(platform(libs.firebase.bom))

    // Firebase Services
    implementation(libs.firebase.firestore)
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.engage.core)
    implementation(libs.firebase.ml.common)
    implementation(libs.ui.graphics)

    // Testing
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
    implementation(libs.materialIconsExtended) // Add this line
    implementation(libs.quarks)
    implementation(libs.parabolic.motion.animation)


}
