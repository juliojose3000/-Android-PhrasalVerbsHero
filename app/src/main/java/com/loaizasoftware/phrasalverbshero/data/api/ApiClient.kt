package com.loaizasoftware.phrasalverbshero.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val BASE_URL = "http://192.168.0.228:8080/phrasalverbshero/"  // For emulator; use "http://your_ip:8080" for real device

    val retrofit: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
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