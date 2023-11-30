plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.mycommonutilslibrary"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mycommonutilslibrary"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {

        create("DEBUG") {
            storeFile = file("${rootDir}/development_keystore.jks")
            storePassword = "123456"
            keyAlias = "development_keystore"
            keyPassword = "123456"
        }

        create("RELEASE") {
            storeFile = file("${rootDir}/production_keystore.jks")
            storePassword = "123456"
            keyAlias = "production_keystore"
            keyPassword = "123456"
        }
    }

    flavorDimensions += "default"

    productFlavors {

        create("dev") {
            dimension = "default"
            applicationIdSuffix = ".dev"
        }

        create("prod") {
            dimension = "default"
        }
    }

    android.applicationVariants.all {

        when (flavorName) {
            "dev" -> {
                buildConfigField("String","BaseURL", project.findProperty("DEV_BASE_URL").toString())
            }

            "prod" -> {
                buildConfigField("String","BaseURL", project.findProperty("PROD_BASE_URL").toString())
            }
        }
    }

    buildTypes {

        debug {
            isMinifyEnabled = false
            isDebuggable = true
            signingConfig = signingConfigs["DEBUG"]
        }

        release {
            isDebuggable = false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs["RELEASE"]
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
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Using local module of the project
    //implementation(project(":OurBaseUtils"))

    //Using published module from jitpack.io
    implementation("com.github.DharmeshBasapatiBacancy:MyCommonUtilsLibrary:1.0.5")
}