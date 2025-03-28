package com.loaizasoftware.core_ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {
    val error: MutableLiveData<Throwable> = MutableLiveData()
    val isLoading: MutableState<Boolean> = mutableStateOf(false)
    val events: MutableSharedFlow<String> = MutableSharedFlow()

    protected fun sendEvent(message: String) {
        viewModelScope.launch {
            events.emit(message) // ✅ Emits a one-time event
        }
    }

}