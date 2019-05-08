package com.samkazmi.example.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.samkazmi.example.BuildConfig
import com.samkazmi.example.di.activitylevel.RepositoryModule
import com.samkazmi.example.di.scope.AppScope


import samkazmi.example.repositories.remote.client.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import samkazmi.example.base.utils.Constants
import samkazmi.example.repositories.utils.ParseErrors
import samkazmi.example.repositories.utils.PreferencesHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.joda.time.DateTime
import org.joda.time.LocalDate
import retrofit2.Retrofit

import java.util.concurrent.TimeUnit

@Module(includes = [RepositoryModule::class])
class AppModule {

    @Provides
    @AppScope
    internal fun retrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonCustomConverterFactory.create(gson))
            .client(client).build()
    }


    @Provides
    @AppScope
    internal fun okHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        keyAuth: ApiKeyAuth,
        sessionAuth: SessionAuth
    ): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(keyAuth)
            .addInterceptor(sessionAuth)
            .build()
    }

    @Provides
    @AppScope
    internal fun gson(): Gson {
        return GsonBuilder()
            .setDateFormat(Constants.DATE_FORMAT_PATTERN)
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(DateTime::class.java, DateTimeTypeAdapter())
            .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
            .setPrettyPrinting()
            .create()
    }

    @Provides
    @AppScope
    internal fun parseErrors(gson: Gson): ParseErrors {
        return ParseErrors(gson)
    }

    @Provides
    @AppScope
    internal fun apiKeyAuth(): ApiKeyAuth {
        val apiKeyAuth = ApiKeyAuth("header", "api")
        apiKeyAuth.apiKey = BuildConfig.API_KEY
        return apiKeyAuth
    }


    @Provides
    @AppScope
    internal fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @AppScope
    internal fun sharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
    }

    @Provides
    @AppScope
    internal fun preferencesHelper(preferences: SharedPreferences): PreferencesHelper {
        return PreferencesHelper(preferences)
    }

    @Provides
    internal fun context(application: Application): Context {
        return application.applicationContext
    }

}
