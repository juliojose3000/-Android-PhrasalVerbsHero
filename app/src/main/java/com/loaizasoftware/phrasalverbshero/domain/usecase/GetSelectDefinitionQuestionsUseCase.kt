package com.loaizasoftware.phrasalverbshero.domain.usecase

import com.loaizasoftware.phrasalverbshero.core.UseCase
import com.loaizasoftware.phrasalverbshero.domain.model.Question
import com.loaizasoftware.phrasalverbshero.domain.repository.QuestionRepository
import io.reactivex.Single
import javax.inject.Inject

class GetSelectDefinitionQuestionsUseCase @Inject constructor(private val repository: QuestionRepository):
    UseCase<Single<List<Question>>, String>() {

    override fun run(params: String): Single<List<Question>> {
        return repository.getQuestions(params)
    }

}




