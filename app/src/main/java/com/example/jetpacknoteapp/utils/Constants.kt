package com.example.jetpacknoteapp.utils

import androidx.compose.ui.graphics.Color
import com.example.jetpacknoteapp.ui.theme.*
import javax.inject.Singleton

@Singleton
class Constants {
    companion object{
        val noteColorsList = listOf(SearchGray, Orange, Red, Blue, Color.Green, Purple40)
    }
}