package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import com.loaizasoftware.phrasalverbshero.domain.usecase.FakeGetPhrasalVerbsUseCase


class FakePhrasalVerbViewModel : PhrasalVerbsViewModel(FakeGetPhrasalVerbsUseCase()) {
    init {
        isLoading.value = false
        phrasalVerbsState.value = listOf(
            PhrasalVerb(id = 1L, phrasalVerb = "Go off", meanings = emptyList()),
            PhrasalVerb(id = 2L, phrasalVerb = "Go on", meanings = emptyList())
        )
    }
}