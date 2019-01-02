package com.necatisozer.movievault.data.source.remote.tmdb

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.necatisozer.movievault.app.CacheDir
import com.necatisozer.movievault.app.TmdbApiKey
import com.necatisozer.movievault.data.source.remote.DateAdapter
import com.necatisozer.movievault.data.source.remote.StatusAdapter
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
    val TIMEOUT_IN_SECONDS: Long = 30
    val CACHE_SIZE_IN_BYTES: Long = 10 * 1024 * 1024

    @Singleton
    @Provides
    @TmdbInterceptor
    fun provideRequestInterceptor(@TmdbApiKey apiKey: String) = Interceptor { chain ->
        val originalRequest = chain.request()

        val url = originalRequest.url().newBuilder()
            .addQueryParameter("api_key", apiKey)
            .addQueryParameter("language", Locale.getDefault().toLanguageTag())
            .addQueryParameter("region", Locale.getDefault().country)
            .build()

        val request = originalRequest.newBuilder().url(url).build()
        chain.proceed(request)
    }

    @Singleton
    @Provides
    @TmdbClient
    fun provideOkHttpClient(
        @CacheDir cacheDir: File,
        @TmdbInterceptor requestInterceptor: Interceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .cache(Cache(File(cacheDir, "tmdb_cache"), CACHE_SIZE_IN_BYTES))
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
    fun provideTmdbApi(@TmdbRetrofit retrofit: Retrofit) = retrofit.create(TmdbApi::class.java)

    companion object ApiPaths {
        val API_HOST = "api.themoviedb.org"
        val API_VERSION = "3"
        val API_BASE_URL = "https://$API_HOST/$API_VERSION/"

        val BASE_POSTER_PATH = "http://image.tmdb.org/t/p/w342"
        val BASR_BACKDROP_PATH = "http://image.tmdb.org/t/p/w780"
        val YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%1\$s"
        val YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%1\$s/0.jpg"

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