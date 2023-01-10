package com.playground.doggies.data.remote

import android.util.Log
import com.playground.doggies.domain.DoggiesRemoteStore
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteModule {

    @Binds
    abstract fun provideRemoteStore(doggiesClient: DoggiesClient): DoggiesRemoteStore

    companion object {

        @Provides
        fun provideMoshi(): Moshi {
            return Moshi
                .Builder()
                .build()
        }

        @Provides
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor { Log.d("Interceptor", it) }
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        @Provides
        fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient
                .Builder()
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(false)
                .build()
        }

        @Provides
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
            moshi: Moshi
        ): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl(API)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        @Singleton
        @Provides
        fun provideBreedsApi(retrofit: Retrofit): BreedsApi {
            return retrofit.create(BreedsApi::class.java)
        }
    }

}