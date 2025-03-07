package com.loaizasoftware.phrasalverbshero.data.api

import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api/verbs")
    fun getVerbs(): Call<List<Verb>>

    @GET("api/verbs")
    fun getVerbsSingle(): Single<List<Verb>>

}