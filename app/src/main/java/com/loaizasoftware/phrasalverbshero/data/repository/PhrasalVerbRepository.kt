package com.loaizasoftware.phrasalverbshero.data.repository

import com.loaizasoftware.phrasalverbshero.data.api.ApiService
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import io.reactivex.Single

open class PhrasalVerbRepository(private val apiService: ApiService) {

    fun getPhrasalVerbs(verbId: Long): Single<List<PhrasalVerb>> {
        return apiService.getPhrasalVerbs(verbId)
    }

}