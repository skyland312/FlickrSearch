package com.swlee.flickrsearcher.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.flickr.com/"
private const val CONNECTION_TIMEOUT_MS: Long = 10

object WebClient {
    val client: ApiService by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create()
            )
        ).client(
            OkHttpClient.Builder().connectTimeout(
                CONNECTION_TIMEOUT_MS, TimeUnit.SECONDS
            ).addInterceptor(okhttp3.logging.HttpLoggingInterceptor().apply {
                level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
            }).build()
        ).build().create(ApiService::class.java)
    }
}