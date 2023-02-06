package com.example.jetpacknoteapp.domain.model

import com.example.jetpacknoteapp.data.local.entity.NoteEntity


data class NoteModel(
    val id: Int?=null,
    val noteTitle: String,
    val noteSubtitle: String,
    val noteText: String,
    val dateTime: String,
    val imagePath: String?,
    val noteColor: Int,
    val webLink: String?,
) {
    fun toEntity(): NoteEntity = NoteEntity(
        id = id,
        title = noteTitle,
        subtitle = noteSubtitle,
        noteText = noteText,
        dateTime = dateTime,
        imagePath = imagePath,
        color = noteColor,
        webLink = webLink
    )
}