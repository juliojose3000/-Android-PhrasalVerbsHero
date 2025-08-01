package com.loaizasoftware.phrasalverbshero.domain.repository

import com.loaizasoftware.phrasalverbshero.core.network.ApiResult
import com.loaizasoftware.phrasalverbshero.domain.model.Meaning
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import io.reactivex.Single

interface PhrasalVerbRepository {

    fun getPhrasalVerbs(verbId: Long): Single<List<PhrasalVerb>>

    fun getPhrasalVerbsSafely(verbId: Long): Single<ApiResult<List<PhrasalVerb>>>

    fun getPhrasalVerbMeanings(phrasalVerbId: Long): Single<List<Meaning>>

}