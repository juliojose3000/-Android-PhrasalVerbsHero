package com.loaizasoftware.phrasalverbshero.domain.usecase

import com.loaizasoftware.phrasalverbshero.core.None
import com.loaizasoftware.phrasalverbshero.core.UseCase
import com.loaizasoftware.phrasalverbshero.domain.repository.VerbRepository
import io.reactivex.Single
import javax.inject.Inject

open class GetPrepsAdverbsUseCase @Inject constructor(private val repository: VerbRepository): UseCase<Single<List<String>>, None>() {

    override fun run(params: None): Single<List<String>> {
        return repository.getPrepsAdverbs()
    }

}
