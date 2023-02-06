package com.example.jetpacknoteapp.ui.detail_add_screen.uiStates

sealed class UiEvent{
    object SaveNote: UiEvent()
    object DeleteNote: UiEvent()
}
