package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import com.loaizasoftware.phrasalverbshero.domain.model.Answer
import com.loaizasoftware.phrasalverbshero.domain.model.Question
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetSelectDefinitionQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel @Inject constructor(private val getSelectDefinitionsUseCase: GetSelectDefinitionQuestionsUseCase): BaseViewModel() {

    val questionsList = mutableStateListOf<Question>()

    val selectedAnswers = mutableStateMapOf<Int, Long?>() // Map to hold selected answers for each question

    val shuffledAnswersList = mutableStateListOf<List<Answer>>() // Store shuffled answers for each question

    @SuppressLint("CheckResult")
    fun getSelectDefinitionsQuestions(verbId: Long) {

        getSelectDefinitionsUseCase.run(verbId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doFinally { isLoading.value = false }
            .subscribe({

                questionsList.addAll(it)

                //Store all the questions answers shuffled on a mutableStateList
                shuffledAnswersList.addAll(it.map { question ->
                    question.getAllAnswers().shuffled() // Shuffle only once
                })

            }, {
                error.value = it
                Timber.e(it)
            })

    }

    // Method to update selected answer for each question
    fun updateSelectedAnswer(questionIndex: Int, answerId: Long?) {
        selectedAnswers[questionIndex] = answerId
    }

    override fun onCleared() {
        super.onCleared()
        questionsList.clear()
        selectedAnswers.clear()
        shuffledAnswersList.clear()
    }

}