package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loaizasoftware.phrasalverbshero.data.repository.VerbRepository
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetVerbsUseCase

class VerbViewModelFactory(private val repository: VerbRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VerbViewModel::class.java)) {
            return VerbViewModel(GetVerbsUseCase(repository)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}