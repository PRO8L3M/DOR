@file:Suppress("MayBeConstant")

object Versions {
    val kotlin = "1.3.72"
    val buildGradle = "3.6.3"

    val appCompat = "1.1.0"
    val coreKtx = "1.2.0"
    val constraintLayout = "1.1.3"

    val junit = "4.12"
    val androidxJunit = "1.1.1"
    val androidxEspresso = "3.2.0"
    val mockito = "3.3.3"
    val coroutinesTest = "1.3.4"
    val archTesting = "2.1.0"

    val koin = "2.1.0-beta-1"

    val coroutines = "1.3.5"

    val material = "1.1.0"

    val leakCanary = "2.0"

    val timber = "4.7.1"

    val legacy = "1.0.0"

    val lifecycleKtx = "2.3.0-alpha01"

    val navigation = "2.3.0-alpha01"

    val gradleVersionsPlugin = "0.28.0"

    val retrofit = "2.6.0"
    val moshi = "2.4.0"
    val moshiConverter = "2.6.0"
    val okhttp = "4.1.0"

    val room = "2.2.5"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val kotlinTest= "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"

    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val buildGradle = "com.android.tools.build:gradle:${Versions.buildGradle}"
    val androidxLegacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"

    val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val androidxCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    val junit = "junit:junit:${Versions.junit}"
    val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    val androidxEspresso = "androidx.test.espresso:espresso-core:${Versions.androidxEspresso}"
    val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    val archCoreTesting = "androidx.arch.core:core-testing:${Versions.archTesting}"
    val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"

    val koin = "org.koin:koin-core:${Versions.koin}"
    val koinExt = "org.koin:koin-core-ext:${Versions.koin}"
    val koinAndroidxExt= "org.koin:koin-androidx-ext:${Versions.koin}"
    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinAndroidxScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    val koinAndroidxViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    val koinTest = "org.koin:koin-test:${Versions.koin}"

    val viewModelScopeKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}"
    val lifecycleScopeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}"
    val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleKtx}"

    val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val material = "com.google.android.material:material:${Versions.material}"

    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val gradleVersionPlugin = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersionsPlugin}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.moshi}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter}"
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    val room = "androidx.room:room-runtime:${Versions.room}"
    val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
}