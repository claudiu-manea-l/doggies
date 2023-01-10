object Config {
    const val build_tools = "30.0.3"
    const val targetSdk = 33
    const val compileSdk = 33
}

object Kotlin {
    private const val base = "org.jetbrains.kotlinx"
    const val coroutines = "$base:kotlinx-coroutines-core:1.6.4"
}

object Dagger {
    private const val version = "2.44.2"
    private const val base = "com.google.dagger"

    const val hilt_android = "$base:hilt-android:$version"
    const val hilt_compiler = "$base:hilt-compiler:$version"

    const val javax_inject = "javax.inject:javax.inject:1"
}

object AndroidX {
    private const val v_core_ktx = "1.9.0"
    private const val v_appcompat = "1.5.1"
    private const val v_constraint = "2.1.4"
    private const val v_material = "1.7.0"
    private const val v_fragment = "1.5.5"

    const val core_ktx = "androidx.core:core-ktx:$v_core_ktx"
    const val appcompat = "androidx.appcompat:appcompat:$v_appcompat"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:$v_constraint"
    const val material = "com.google.android.material:material:$v_material"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:$v_fragment"
}

object Others {
    private const val v_glide = "4.12.0"
    private const val v_arrow = "1.1.2"

    const val glide = "com.github.bumptech.glide:glide:$v_glide"
    const val glide_compiler = "com.github.bumptech.glide:compiler:$v_glide"
    const val arrow_core = "io.arrow-kt:arrow-core:$v_arrow"
}

object Navigation {
    private const val version = "2.5.3"
    private const val base = "androidx.navigation"

    const val ui = "$base:navigation-ui-ktx:$version"
    const val fragment = "$base:navigation-fragment-ktx:$version"
}

object Lifecycle {
    private const val version = "2.4.0"
    private const val base = "androidx.lifecycle"
    const val viewmodel_ktx = "$base:lifecycle-viewmodel-ktx:$version"
    const val runtime_ktx = "$base:lifecycle-runtime-ktx:$version"
}


object Retrofit {
    private const val version = "2.9.0"
    private const val base = "com.squareup.retrofit2"

    const val retrofit = "$base:retrofit:$version"
    const val converter_moshi = "$base:converter-moshi:$version"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:4.9.2"
}

object Room {
    private const val version = "2.3.0"
    private const val base = "androidx.room"

    const val runtime = "$base:room-runtime:$version"
    const val room_ktx = "$base:room-ktx:$version"
    const val compiler = "$base:room-compiler:$version"
}

object Testing {
    const val junit = "junit:junit:4.13.2"
    const val junit_ext = "androidx.test.ext:junit:1.1.3" // Move to 1.1.4
    const val espresso = "androidx.test.espresso:espresso-core:3.5.0"
}