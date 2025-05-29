package com.loaizasoftware.phrasalverbshero.domain.repository

import com.loaizasoftware.phrasalverbshero.domain.model.Question
import io.reactivex.Single

interface QuestionRepository {

    fun getQuestions(verbId: String): Single<List<Question>>

}