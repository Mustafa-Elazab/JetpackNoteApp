package com.example.jetpacknoteapp.ui.home_screen.state

sealed class HomeUiEvent {
    data class SearchTextChanged(var text:String):HomeUiEvent()
    object Search:HomeUiEvent()
}