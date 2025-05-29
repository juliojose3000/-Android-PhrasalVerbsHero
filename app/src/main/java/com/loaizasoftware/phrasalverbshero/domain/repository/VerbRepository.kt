package com.loaizasoftware.phrasalverbshero.domain.repository

import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import io.reactivex.Single
import retrofit2.Call

interface VerbRepository {

    fun getVerbs(): Call<List<Verb>>

    fun getVerbsSingle(): Single<List<Verb>>

    fun getPrepsAdverbs(): Single<List<String>>

}