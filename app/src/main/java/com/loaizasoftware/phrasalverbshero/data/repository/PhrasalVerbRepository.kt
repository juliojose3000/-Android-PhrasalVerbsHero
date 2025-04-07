package com.loaizasoftware.phrasalverbshero.data.repository

import com.loaizasoftware.phrasalverbshero.core.network.ApiResult
import com.loaizasoftware.phrasalverbshero.core.network.safeApiCall
import com.loaizasoftware.phrasalverbshero.data.api.ApiService
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

open class PhrasalVerbRepository(private val apiService: ApiService) {

    fun getPhrasalVerbs(verbId: Long): Single<List<PhrasalVerb>> {
        return apiService.getPhrasalVerbs(verbId)
    }

    fun getPhrasalVerbsSafely(verbId: Long): Single<ApiResult<List<PhrasalVerb>>> {
        return Single.fromCallable {
            safeApiCall {
                apiService.getPhrasalVerbs(verbId).blockingGet()
            }
        }.subscribeOn(Schedulers.io())
    }

}