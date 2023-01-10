object Config {
    const val build_tools = "30.0.3"
    const val targetSdk = 33
    const val compileSdk = 33
}

object Dependencies {

    object Retrofit {
        private const val version = "2.9.0"
        private const val base = "com.squareup.retrofit2"

        const val retrofit = "$base:retrofit:${Versions.retrofit}"
        const val converter_moshi = "$base:converter-moshi:${Versions.retrofit}"
    }

    object Dagger {
        private const val version = "2.44.2"
        private const val base = "com.google.dagger"

        const val dagger = "$base:dagger:$version"
        const val dagger_android = "$base:dagger-android:$version"
        const val dagger_android_supp = "$base:dagger-android-supp:$version"
        const val dagger_ap = "$base:dagger-android-processor:$version"
        const val dagger_compiler = "$base:dagger-compiler:$version"
        const val hilt_android = "$base:hilt-android:$version"
        const val hilt_compiler = "$base:hilt-compiler:$version"

    }
}



object Versions {

    const val kotlin = "1.7.20"


    const val javaxInject = "1"

    const val okhttp = ""
    const val retrofit = "2.9.0"

}