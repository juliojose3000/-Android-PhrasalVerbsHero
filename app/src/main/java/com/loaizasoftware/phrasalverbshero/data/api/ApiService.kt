package com.loaizasoftware.phrasalverbshero.data.api

import com.loaizasoftware.phrasalverbshero.domain.model.Meaning
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("api/verbs")
    fun getVerbs(): Call<List<Verb>>

    @GET("api/verbs")
    fun getVerbsSingle(): Single<List<Verb>>

    @GET("api/phrasalverbs/getByVerbId")
    fun getPhrasalVerbs(@Query("verbId") verbId: Long): Single<List<PhrasalVerb>>

    @GET("api/phrasalverbs/getMeanings")
    fun getPhrasalVerbMeanings(@Query("phrasalVerbId") phrasalVerbId: Long): Single<List<Meaning>>

}