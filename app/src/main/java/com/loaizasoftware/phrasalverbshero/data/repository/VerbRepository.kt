package com.loaizasoftware.phrasalverbshero.data.repository

import com.loaizasoftware.phrasalverbshero.data.api.ApiService
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import retrofit2.Call

class VerbRepository(private val apiService: ApiService) {
    fun getVerbs(): Call<List<Verb>> {
        return apiService.getVerbs()  // Just delegates to ApiService
    }
}