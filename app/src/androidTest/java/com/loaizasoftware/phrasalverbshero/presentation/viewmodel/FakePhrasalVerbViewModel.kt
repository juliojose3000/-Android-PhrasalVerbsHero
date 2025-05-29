package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import com.loaizasoftware.phrasalverbshero.domain.usecase.FakeGetDefinitionsUseCase
import com.loaizasoftware.phrasalverbshero.domain.usecase.FakeGetPhrasalVerbsByPart
import com.loaizasoftware.phrasalverbshero.domain.usecase.FakeGetPhrasalVerbsUseCase


class FakePhrasalVerbViewModel : PhrasalVerbsViewModel(FakeGetPhrasalVerbsUseCase(), FakeGetDefinitionsUseCase(), FakeGetPhrasalVerbsByPart()) {
    init {
        isLoading.value = false
        phrasalVerbsState.value = listOf(
            PhrasalVerb(id = 1L, phrasalVerb = "Go off", definitions = emptyList()),
            PhrasalVerb(id = 2L, phrasalVerb = "Go on", definitions = emptyList())
        )
    }
}