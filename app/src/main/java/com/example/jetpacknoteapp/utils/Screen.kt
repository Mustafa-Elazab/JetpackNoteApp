package com.example.jetpacknoteapp.utils

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home_screen")
    object NoteDetailsScreen: Screen("note_details_screen")
}
