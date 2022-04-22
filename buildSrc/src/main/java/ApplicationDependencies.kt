object AppDependencies {
    private const val appcompat = "androidx.appcompat:appcompat:${ApplicationVersions.appcompat}"
    private const val coreKtx = "androidx.core:core-ktx:${ApplicationVersions.coreKtx}"
    private const val constraintLayout = "androidx.constraintlayout:constraintlayout:${ApplicationVersions.constraintLayout}"
    private const val material = "com.google.android.material:material:${ApplicationVersions.material}"
    private const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${ApplicationVersions.navigation}"
    private const val navigationUi = "androidx.navigation:navigation-ui-ktx:${ApplicationVersions.navigation}"

    private const val junit = "junit:junit:${ApplicationVersions.junit}"
    private const val extJUnit = "androidx.test.ext:junit:${ApplicationVersions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${ApplicationVersions.espresso}"

    val applicationLibraries = arrayListOf<String>().apply {
        add(appcompat)
        add(coreKtx)
        add(constraintLayout)
        add(material)
        add(navigationFragment)
        add(navigationUi)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }
}