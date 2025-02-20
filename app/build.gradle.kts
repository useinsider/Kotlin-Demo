plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.useinsider.kotlindemo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.useinsider.kotlindemo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        // TODO: Please change with your partner name. You can find the partner name after login
        //  into the Insider panel. The left side of your mail address.
        manifestPlaceholders["partner"] = "your_partner_name"
        manifestPlaceholders["googleAdsAppId"] = project.findProperty("GOOGLE_ADS_APP_ID") ?: ""

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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
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

    //Required
    implementation(libs.insider.sdk)
    implementation(libs.firebase.messaging)
    implementation(libs.lifecycle.process)
    implementation(libs.security.crypto)

    implementation(libs.huawei.push)
    implementation(libs.huawei.ads)
    implementation(libs.huawei.location)

    //Optional for Geofence
    implementation(libs.play.services.location)

    //Optional for logging
    implementation(libs.timber)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}