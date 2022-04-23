import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    id(ApplicationConfiguration.androidApplicationPlugin)
    id(ApplicationConfiguration.jetbrainsKotlinAndroidPlugin)
    id(ApplicationConfiguration.kotlinKaptPlugin)
}
android {
    compileSdk = ApplicationConfiguration.compileSdk

    defaultConfig {
        applicationId = ApplicationConfiguration.packageName

        minSdk = ApplicationConfiguration.minSdk
        targetSdk = ApplicationConfiguration.targetSdk
        versionCode = ApplicationConfiguration.versionCode
        versionName = ApplicationConfiguration.versionName

        testInstrumentationRunner = ApplicationConfiguration.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions { jvmTarget = "1.8" }

    flavorDimensions.add("version")
    productFlavors {
        create("red_camera") {
            dimension = "version"
            applicationIdSuffix = ".camera"
            buildConfigField("String", "fileSourceType", "\"camera\"")
            resValue("color", "theme_color_500", "#F44336")
            resValue("color", "theme_color_700", "#D32F2F")
        }
        create("red_filesystem") {
            dimension = "version"
            applicationIdSuffix = ".filesystem"
            buildConfigField("String", "fileSourceType", "\"filesystem\"")
            resValue("color", "theme_color_500", "#F44336")
            resValue("color", "theme_color_700", "#D32F2F")
        }
        create("green_camera") {
            dimension = "version"
            applicationIdSuffix = ".camera"
            buildConfigField("String", "fileSourceType", "\"camera\"")
            resValue("color", "theme_color_500", "#4CAF50")
            resValue("color", "theme_color_700", "#388E3C")
        }
        create("green_filesystem") {
            dimension = "version"
            applicationIdSuffix = ".filesystem"
            buildConfigField("String", "fileSourceType", "\"filesystem\"")
            resValue("color", "theme_color_500", "#4CAF50")
            resValue("color", "theme_color_700", "#388E3C")
        }
    }

    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as BaseVariantOutputImpl }
            .forEach { output ->
                output.outputFileName =
                    helpers.ApplicationHelper.renameApkFileName(variant.baseName)
            }
    }
}

dependencies {
    ApplicationDependencies.applicationLibraries.forEach {
        implementation(it)
    }

    ApplicationDependencies.kaptLibraries.forEach {
        kapt(it)
    }

    ApplicationDependencies.testLibraries.forEach {
        testImplementation(it)
    }

    ApplicationDependencies.androidTestLibraries.forEach {
        androidTestImplementation(it)
    }
}