package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    val error: MutableLiveData<Throwable> = MutableLiveData()
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
}