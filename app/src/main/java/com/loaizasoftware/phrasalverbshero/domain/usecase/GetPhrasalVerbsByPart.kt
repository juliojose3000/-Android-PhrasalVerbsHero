package com.loaizasoftware.phrasalverbshero.domain.usecase

import com.loaizasoftware.phrasalverbshero.core.UseCase
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import com.loaizasoftware.phrasalverbshero.domain.repository.PhrasalVerbRepository
import io.reactivex.Single
import javax.inject.Inject


open class GetPhrasalVerbsByPart @Inject constructor(private val repository: PhrasalVerbRepository): UseCase<Single<List<PhrasalVerb>>, String>() {

    override fun run(params: String): Single<List<PhrasalVerb>> {
        return repository.getPhrasalVerbs(params)
    }

}