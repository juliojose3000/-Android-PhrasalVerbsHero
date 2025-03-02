plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    //Firebase Crashlytics
    id("com.google.firebase.crashlytics")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.loaizasoftware.phrasalverbshero"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.loaizasoftware.phrasalverbshero"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    flavorDimensions += "environment"  // Defines the dimension for flavors

    productFlavors {
        create("qa") {
            dimension = "environment"
            //applicationIdSuffix = ".qa"  // App ID will be different for QA
            versionNameSuffix = "-QA"
            buildConfigField ("String", "BASE_URL", "\"https://phrasalverbshero.fly.dev/phrasalverbshero/\"")
        }

        create("prod") {
            dimension = "environment"
            buildConfigField ("String", "BASE_URL", "\"https://phrasalverbshero.fly.dev/phrasalverbshero/\"")
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
        buildConfig = true //it allows us to implement the product flavors in the build.gradle.kts file
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    //Retrofit + Gson -> https://github.com/square/retrofit | https://github.com/google/gson
    //implementation(libs.retrofit)
    //implementation(libs.converter.gson)

    //Retrofit + Moshi -> https://github.com/square/moshi
    implementation (libs.moshi.v1150)
    implementation (libs.moshi.kotlin)
    implementation (libs.converter.moshi)

    //Chucker -> https://github.com/ChuckerTeam/chucker
    debugImplementation (libs.chucker.library)
    releaseImplementation (libs.library.no.op)

    //--------- Firebase ---------//
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    //Crashlytics -> https://firebase.google.com/docs/crashlytics/get-started?platform=android#add-sdk
    implementation(libs.firebase.crashlytics)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}