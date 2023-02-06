package com.example.jetpacknoteapp.ui.home_screen.state

import androidx.compose.ui.graphics.toArgb
import com.example.jetpacknoteapp.utils.Constants

data class NoteItemUiState(
    var id: Int = -1,
    var noteTitle: String = "",
    var noteSubtitle: String = "",
    var noteDate: String = "",
    var noteColor: Int = Constants.noteColorsList[0].toArgb(),
    var imagePath: String? = null,
    var isImageVisible: Boolean = false
)