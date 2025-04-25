package com.loaizasoftware.phrasalverbshero.domain.usecase

import com.loaizasoftware.phrasalverbshero.core.None
import com.loaizasoftware.phrasalverbshero.core.UseCase
import com.loaizasoftware.phrasalverbshero.data.repository.VerbRepositoryImpl
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import com.loaizasoftware.phrasalverbshero.domain.repository.VerbRepository
import io.reactivex.Single
import retrofit2.Call
import javax.inject.Inject

open class GetVerbsUseCase @Inject constructor(private val repository: VerbRepository): UseCase<Single<List<Verb>>, None>() {

    fun execute(): Call<List<Verb>> {
        return repository.getVerbs()
    }

    override fun run(params: None): Single<List<Verb>> {
        return repository.getVerbsSingle()
    }

}
