package com.loaizasoftware.phrasalverbshero.domain.usecase

import com.loaizasoftware.phrasalverbshero.core.UseCase
import com.loaizasoftware.phrasalverbshero.domain.model.Definition
import com.loaizasoftware.phrasalverbshero.domain.repository.PhrasalVerbRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetDefinitionsUseCase @Inject constructor(private val repository: PhrasalVerbRepository): UseCase<Single<List<Definition>>, Long>() {

    override fun run(params: Long): Single<List<Definition>> {

        return repository.getPhrasalVerbDefinitions(params)

    }

}