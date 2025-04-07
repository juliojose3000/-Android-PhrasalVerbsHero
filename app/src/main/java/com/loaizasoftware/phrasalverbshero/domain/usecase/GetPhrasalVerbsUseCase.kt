package com.loaizasoftware.phrasalverbshero.domain.usecase

import com.loaizasoftware.phrasalverbshero.core.UseCase
import com.loaizasoftware.phrasalverbshero.core.network.ApiResult
import com.loaizasoftware.phrasalverbshero.data.repository.PhrasalVerbRepository
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import io.reactivex.Single
import javax.inject.Inject

open class GetPhrasalVerbsUseCase @Inject constructor(private val repository: PhrasalVerbRepository): UseCase<Single<List<PhrasalVerb>>, Long>() {

    fun get(params: Long): Single<ApiResult<List<PhrasalVerb>>> {
        return repository.getPhrasalVerbsSafely(params)
    }

    override fun run(params: Long): Single<List<PhrasalVerb>> {
        return repository.getPhrasalVerbs(params)
    }

}