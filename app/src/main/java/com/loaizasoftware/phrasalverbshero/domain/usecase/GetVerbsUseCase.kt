package com.loaizasoftware.phrasalverbshero.domain.usecase

import com.loaizasoftware.phrasalverbshero.core.None
import com.loaizasoftware.phrasalverbshero.core.UseCase
import com.loaizasoftware.phrasalverbshero.data.repository.VerbRepository
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import io.reactivex.Single
import retrofit2.Call

class GetVerbsUseCase(private val repository: VerbRepository): UseCase<Single<List<Verb>>, None>() {

    fun execute(): Call<List<Verb>> {
        return repository.getVerbs()
    }

    override fun run(params: None): Single<List<Verb>> {
        return repository.getVerbsSingle()
    }

}
