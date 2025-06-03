package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import com.loaizasoftware.core_ui.base.UiState
import com.loaizasoftware.phrasalverbshero.domain.model.Answer
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetSelectDefinitionQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PracticeViewModel @Inject constructor(private val getSelectDefinitionsUseCase: GetSelectDefinitionQuestionsUseCase): BaseViewModel() {

    val selectedAnswers = mutableStateMapOf<Int, Long?>() // Map to hold selected answers for each question

    val shuffledAnswersList = mutableStateListOf<List<Answer>>() // Store shuffled answers for each question

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    /*@SuppressLint("CheckResult")
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

    }*/

    fun setUiState(state: UiState) {
        _uiState.value = state
    }

    @SuppressLint("CheckResult")
    fun getSelectDefinitionsQuestions(phrasalVerbPart: String) {

        getSelectDefinitionsUseCase.run(phrasalVerbPart)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _uiState.value = UiState.Loading }
            .subscribe({

                //Store all the questions answers shuffled on a mutableStateList
                shuffledAnswersList.addAll(it.map { question ->
                    question.getAllAnswers().shuffled() // Shuffle only once
                })

                _uiState.value = UiState.Success(it)

            }, {
                _uiState.value = UiState.Error(it.message ?: "Unknown error")
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
        selectedAnswers.clear()
        shuffledAnswersList.clear()
    }

}