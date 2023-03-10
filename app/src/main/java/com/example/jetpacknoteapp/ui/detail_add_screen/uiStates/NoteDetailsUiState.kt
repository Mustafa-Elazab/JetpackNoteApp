package com.example.jetpacknoteapp.ui.detail_add_screen.uiStates
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.jetpacknoteapp.utils.Constants
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class NoteDetailsUiState(
    var id: Int = -1,
    var titleInputFieldUiState: InputFieldUiState = InputFieldUiState(),
    var subtitleInputFieldUiState: InputFieldUiState = InputFieldUiState(),
    var descriptionInputFieldUiState: InputFieldUiState = InputFieldUiState(),
    var noteColors: List<Color> = Constants.noteColorsList,
    var noteColor: Int = noteColors[0].toArgb(),
    var dateTime: String = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm", Locale.getDefault()).format(
        Date()
    ),
    var imagePath: String? = null,
    var linkUiState: LinkUiState = LinkUiState(),
    var isImageVisible: Boolean = false
)