package com.necatisozer.movievault.data.source.tmdb

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.necatisozer.movievault.BuildConfig
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class TmdbModule {

    @Singleton
    @Provides
    @TmdbCache
    fun provideCache(application: Application): Cache =
        Cache(File(application.cacheDir, "tmdb-cache"), CACHE_SIZE_IN_BYTES)

    @Singleton
    @Provides
    @TmdbInterceptor
    fun provideRequestInterceptor() = Interceptor { chain ->
        val originalRequest = chain.request()

        val url = originalRequest.url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
            .addQueryParameter("language", Locale.getDefault().toLanguageTag())
            .addQueryParameter("region", Locale.getDefault().country)
            .build()

        val request = originalRequest.newBuilder().url(url).build()
        chain.proceed(request)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

    @Singleton
    @Provides
    @TmdbClient
    fun provideOkHttpClient(
        @TmdbCache cache: Cache,
        @TmdbInterceptor requestInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor(requestInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    @TmdbMoshi
    fun provideMoshi() = Moshi.Builder()
        .add(Wrapped.ADAPTER_FACTORY)
        .add(DateAdapter())
        .add(StatusAdapter())
        .build()

    @Singleton
    @Provides
    @TmdbRetrofit
    fun provideRetrofit(@TmdbClient okHttpClient: OkHttpClient, @TmdbMoshi moshi: Moshi) =
        Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideTmdbApi(@TmdbRetrofit retrofit: Retrofit) = retrofit.create(
        TmdbApi::class.java
    )

    companion object ApiPaths {
        private const val TIMEOUT_IN_SECONDS: Long = 30
        private const val CACHE_SIZE_IN_BYTES: Long = 10 * 1024 * 1024

        private const val API_HOST = "api.themoviedb.org"
        private const val API_VERSION = "3"
        private const val API_BASE_URL = "https://$API_HOST/$API_VERSION/"

        private const val BASE_POSTER_PATH = "http://image.tmdb.org/t/p/w342"
        private const val BASR_BACKDROP_PATH = "http://image.tmdb.org/t/p/w780"
        private const val YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%1\$s"
        private const val YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%1\$s/0.jpg"

        fun getPosterUrl(posterPath: String): String {
            return BASE_POSTER_PATH + posterPath
        }

        fun getBackdropUrl(backdropPath: String): String {
            return BASR_BACKDROP_PATH + backdropPath
        }
    }
}

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class TmdbCache

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class TmdbInterceptor

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class TmdbClient

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class TmdbMoshi

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class TmdbRetrofit