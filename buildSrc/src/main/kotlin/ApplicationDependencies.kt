object ApplicationDependencies {
    private const val appcompat = "androidx.appcompat:appcompat:${ApplicationVersions.appcompat}"
    private const val fragmentKts = "androidx.fragment:fragment-ktx:${ApplicationVersions.fragmentKtx}"
    private const val coreKtx = "androidx.core:core-ktx:${ApplicationVersions.coreKtx}"
    private const val constraintLayout = "androidx.constraintlayout:constraintlayout:${ApplicationVersions.constraintLayout}"
    private const val material = "com.google.android.material:material:${ApplicationVersions.material}"
    private const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${ApplicationVersions.navigation}"
    private const val navigationUi = "androidx.navigation:navigation-ui-ktx:${ApplicationVersions.navigation}"
    private const val dagger = "com.google.dagger:dagger:${ApplicationVersions.dagger}"
    private const val kaptDagger = "com.google.dagger:dagger-compiler:${ApplicationVersions.dagger}"
    private const val timber = "com.jakewharton.timber:timber:${ApplicationVersions.timber}"
    private const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${ApplicationVersions.coroutinesAndroid}"
    private const val lifecycleCommon = "androidx.lifecycle:lifecycle-common:${ApplicationVersions.lifecycle}"
    private const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${ApplicationVersions.lifecycle}"
    private const val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${ApplicationVersions.lifecycle}"
    private const val coil = "io.coil-kt:coil:${ApplicationVersions.coil}"
    private const val mlKitTextRecognition = "com.google.android.gms:play-services-mlkit-text-recognition:${ApplicationVersions.mlKitTextRecognition}"

    private const val junit = "junit:junit:${ApplicationVersions.junit}"
    private const val extJUnit = "androidx.test.ext:junit:${ApplicationVersions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${ApplicationVersions.espresso}"

    val applicationLibraries = listOf(
        appcompat,
        fragmentKts,
        coreKtx,
        constraintLayout,
        material,
        navigationFragment,
        navigationUi,
        dagger,
        timber,
        coroutinesAndroid,
        lifecycleCommon,
        lifecycleRuntimeKtx,
        lifecycleLivedataKtx,
        coil,
        mlKitTextRecognition
    )

    val kaptLibraries = listOf(kaptDagger)

    val testLibraries = listOf(junit)

    val androidTestLibraries = listOf(
        extJUnit,
        espressoCore
    )
}