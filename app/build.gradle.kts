import java.util.Properties
import kotlin.apply

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    //Room and Hilt
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization") version "2.1.10"
    //Hilt
    id("com.google.dagger.hilt.android")
}
//Load from local.properties
val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) {
        load(file.inputStream())
    }
}

val apiToken = localProperties.getProperty("API_TOKEN") ?: ""
android {
    namespace = "com.example.ballifyandroid"
    compileSdk {
        version = release(36)
    }

    // Enable BuildConfig generation
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        applicationId = "com.example.ballifyandroid"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "API_TOKEN", "\"$apiToken\"")

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true ////

    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    //viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose-android:2.8.7")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")



    // JSON serialization library
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")
    // Jetpack Compose integration
    implementation("androidx.navigation:navigation-compose:2.8.8")


    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    // Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.57.2")
    ksp("com.google.dagger:hilt-android-compiler:2.57.2")

    // Hilt Navigation Compose (for hiltViewModel)
    implementation ("androidx.hilt:hilt-navigation-compose:1.3.0")

    // ViewModel & Compose
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")

    //Async Image
    implementation("io.coil-kt:coil-compose:2.7.0")

}