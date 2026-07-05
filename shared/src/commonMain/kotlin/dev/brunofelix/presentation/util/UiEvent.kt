package dev.brunofelix.presentation.util

sealed interface UiEvent {
    data class ShowErrorMessage(val exception: Throwable) : UiEvent
    object ShowSuccessMessage : UiEvent
}