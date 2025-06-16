package com.loaizasoftware.core_ui.base

sealed interface UiEvent {
    data class ShowToast(val message: String) : UiEvent
    data class Navigate(val route: String) : UiEvent
    object GoBack : UiEvent
}