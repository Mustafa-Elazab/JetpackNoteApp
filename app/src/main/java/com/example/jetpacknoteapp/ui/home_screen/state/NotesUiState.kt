package com.example.jetpacknoteapp.ui.home_screen.state

data class NotesUiState(
    var isLoading: Boolean = true,
    var notes: List<NoteItemUiState> = emptyList(),
    var searchText: String="",
    var searchResult: List<NoteItemUiState> = emptyList()
)