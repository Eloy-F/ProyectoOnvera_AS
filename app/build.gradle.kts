plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.eloy.code.proyectoonvera_as"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.eloy.code.proyectoonvera_as"
        minSdk = 26
        targetSdk = 36
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.datastore.core)

    // Retrofit + OkHttp
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)

    // RecyclerView (AndroidX)
    implementation(libs.recyclerview)

    // Lombok
    compileOnly(libs.lombok)
    annotationProcessor(libs.lombok.annotationProcessor)

    implementation(libs.glide)
    annotationProcessor(libs.glideCompiler)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

}