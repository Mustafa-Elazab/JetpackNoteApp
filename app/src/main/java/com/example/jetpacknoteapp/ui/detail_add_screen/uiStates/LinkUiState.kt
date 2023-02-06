package com.example.jetpacknoteapp.ui.detail_add_screen.uiStates
data class LinkUiState(
    var finalLink: String? = null,
    var typedLink:String ="",
    var linkError: String? = null,
    var isLinkVisible: Boolean = false,
    var isLinkDialogVisible: Boolean = false
)