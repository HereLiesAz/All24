plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("G:\\My Drive\\az_apk_keystore.jks")
            storePassword = "18187077190901818"
            keyAlias = "key0"
            keyPassword = "18187077190901818"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildToolsVersion = "36.0.0"
    ndkVersion = "29.0.13599879 rc2"
}

dependencies {

    // Core Android & Jetpack
    implementation("androidx.core:core-ktx:1.17.0-beta01")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")
    implementation("androidx.activity:activity-compose:1.12.0-alpha04")

    // Jetpack Compose - M3 Expressive Design
    implementation(platform("androidx.compose:compose-bom:2025.07.00"))
    implementation("androidx.compose.ui:ui:1.9.0-beta03")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview:1.9.0-beta03")
    implementation("androidx.compose.material3:material3:1.4.0-alpha18")

    // Firebase - Bill of Materials (BoM)
    implementation(platform("com.google.firebase:firebase-bom:34.0.0"))

    // Firebase Services
    implementation("com.google.firebase:firebase-auth-ktx:23.2.1")
    implementation("com.google.firebase:firebase-firestore-ktx:25.1.4")
    implementation(libs.androidx.navigation.runtime.android)
    implementation(libs.engage.core)

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0-rc01")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0-rc01")
    androidTestImplementation(platform("androidx.compose:compose-bom:2025.07.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.9.0-beta03")
    debugImplementation("androidx.compose.ui:ui-tooling:1.9.0-beta03")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.9.0-beta03")
}
