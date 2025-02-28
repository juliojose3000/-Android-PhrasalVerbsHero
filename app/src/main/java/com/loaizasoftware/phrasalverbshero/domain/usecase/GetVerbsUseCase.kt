package com.loaizasoftware.phrasalverbshero.domain.usecase

import com.loaizasoftware.phrasalverbshero.data.repository.VerbRepository
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import retrofit2.Call

class GetVerbsUseCase(private val repository: VerbRepository) {
    fun execute(): Call<List<Verb>> {
        return repository.getVerbs()
    }
}
