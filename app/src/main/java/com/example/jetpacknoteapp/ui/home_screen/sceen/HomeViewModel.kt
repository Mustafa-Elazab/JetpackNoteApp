package com.example.jetpacknoteapp.ui.home_screen.sceen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpacknoteapp.domain.usecase.GetNotesUseCase
import com.example.jetpacknoteapp.domain.usecase.SearchNotesUseCase
import com.example.jetpacknoteapp.ui.home_screen.state.HomeUiEvent
import com.example.jetpacknoteapp.ui.home_screen.state.NoteItemUiState
import com.example.jetpacknoteapp.ui.home_screen.state.NotesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val searchNotesUseCase: SearchNotesUseCase
) : ViewModel() {

    var notesUiState by mutableStateOf(NotesUiState(isLoading = true))

    private var _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow: SharedFlow<UiEvent> = _eventFlow.asSharedFlow()


    init {
        getNotes()
    }

    private var getNotesJob: Job? = null

    private fun getNotes() {
        getNotesJob?.cancel()
        getNotesJob = viewModelScope.launch {
            getNotesUseCase().collectLatest { notes ->
                val notesListUiItems = notes.map { note ->
                    NoteItemUiState(
                        id = note.id ?: -2,
                        noteTitle = note.noteTitle,
                        noteSubtitle = note.noteSubtitle,
                        noteDate = note.dateTime,
                        noteColor = note.noteColor,
                        imagePath = note.imagePath,
                        isImageVisible = !note.imagePath.isNullOrBlank()
                    )
                }
                notesUiState = notesUiState.copy(
                    isLoading = false,
                    notes = notesListUiItems
                )
            }
        }
    }

    fun onEvent(action: HomeUiEvent) {
        when (action) {
            is HomeUiEvent.SearchTextChanged -> notesUiState =
                notesUiState.copy(searchText = action.text)

            HomeUiEvent.Search -> search()
        }
    }

    fun search() {
        viewModelScope.launch {
            if (notesUiState.searchText.trim().isBlank())
                notesUiState = notesUiState.copy(searchResult = emptyList())
            else {
                try {
                    val searchResult = searchNotesUseCase(notesUiState.searchText)
                    searchResult.collect{
                        val newSearchedList = it.map { note ->
                            NoteItemUiState(
                                id = note.id!!,
                                noteTitle = note.noteTitle,
                                noteSubtitle = note.noteSubtitle,
                                noteDate = note.dateTime,
                                noteColor = note.noteColor,
                                imagePath = note.imagePath,
                                isImageVisible = !note.imagePath.isNullOrBlank()
                            )
                        }
                        notesUiState = notesUiState.copy(searchResult = newSearchedList)
                    }
                } catch (e: Exception) {
                    _eventFlow.emit(UiEvent.ShowMessage(e.message.toString()))
                }
            }
        }
    }
    sealed class UiEvent {
        data class ShowMessage(var message: String) : UiEvent()
    }
}