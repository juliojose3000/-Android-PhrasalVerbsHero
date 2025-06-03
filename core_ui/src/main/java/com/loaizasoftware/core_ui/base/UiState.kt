package com.loaizasoftware.core_ui.base

sealed class UiState {
    object Loading : UiState()
    object OnBackPressed : UiState()
    data class Success(val data: Any) : UiState()
    data class Error(val message: String) : UiState()
}