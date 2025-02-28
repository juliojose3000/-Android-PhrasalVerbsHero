package com.loaizasoftware.phrasalverbshero.data.api

import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api/verbs")
    fun getVerbs(): Call<List<Verb>>

}