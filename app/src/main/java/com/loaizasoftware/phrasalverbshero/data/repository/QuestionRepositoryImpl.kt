package com.loaizasoftware.phrasalverbshero.data.repository

import com.loaizasoftware.phrasalverbshero.data.api.ApiService
import com.loaizasoftware.phrasalverbshero.domain.model.Question
import com.loaizasoftware.phrasalverbshero.domain.repository.QuestionRepository
import io.reactivex.Single

class QuestionRepositoryImpl(private val apiService: ApiService): QuestionRepository {

    override fun getQuestions(verbId: Long): Single<List<Question>> {
        return apiService.getQuestions(verbId)
    }

}