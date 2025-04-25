package com.loaizasoftware.phrasalverbshero.domain.usecase

import com.loaizasoftware.phrasalverbshero.core.UseCase
import com.loaizasoftware.phrasalverbshero.domain.model.Meaning
import com.loaizasoftware.phrasalverbshero.domain.repository.PhrasalVerbRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetMeaningsUseCase @Inject constructor(private val repository: PhrasalVerbRepository): UseCase<Single<List<Meaning>>, Long>() {

    override fun run(params: Long): Single<List<Meaning>> {

        return repository.getPhrasalVerbMeanings(params)

    }

}