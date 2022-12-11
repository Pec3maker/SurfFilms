package ru.surfstudio.base_feature.state

sealed class UiState {

    object Success : UiState()
    data class Error(val uiError: UiError) : UiState()
}