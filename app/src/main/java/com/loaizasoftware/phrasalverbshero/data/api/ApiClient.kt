package com.loaizasoftware.phrasalverbshero.data.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val BASE_URL = "https://phrasalverbshero.fly.dev/phrasalverbshero/"  // For emulator; use "http://your_ip:8080" for real device
    lateinit var retrofit: ApiService

    fun createRetrofit(context: Context) {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()) // Allows Moshi to handle Kotlin classes properly
            .build()

        val client = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context)) // Add Chucker interceptor
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            //.addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)

    }


    companion object {
        private var instance: ApiClient? = null

        fun getInstance(): ApiClient {
            if (instance == null) {
                instance = ApiClient()
            }
            return instance!!
        }
    }

}