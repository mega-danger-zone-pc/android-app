plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.mega_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mega_app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}
dependencies {
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    implementation("androidx.startup:startup-runtime:1.1.1") // Обновлено
    implementation("androidx.appcompat:appcompat:1.6.1") // Уменьшена версия для совместимости
    implementation("com.google.android.material:material:1.10.0") // Проверенная стабильная версия
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") // Проверенная стабильная версия
    implementation("androidx.annotation:annotation:1.6.0")

    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")

    implementation("com.android.support:multidex:1.0.3")

    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.core:core-animation:1.0.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")
    annotationProcessor("com.google.code.gson:gson:2.10.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
