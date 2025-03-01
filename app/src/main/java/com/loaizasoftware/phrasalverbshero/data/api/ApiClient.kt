package com.loaizasoftware.phrasalverbshero.data.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val BASE_URL = "https://phrasalverbshero.fly.dev/phrasalverbshero/"  // For emulator; use "http://your_ip:8080" for real device
    lateinit var retrofit: ApiService

    fun createRetrofit(context: Context) {

        val client = OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context)) // Add Chucker interceptor
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
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