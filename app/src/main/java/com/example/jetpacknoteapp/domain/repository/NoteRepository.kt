package com.example.jetpacknoteapp.domain.repository

import com.example.jetpacknoteapp.domain.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun getNotes(): Flow<List<NoteModel>>
    suspend fun searchNotes(searchKey:String): Flow<List<NoteModel>>
    suspend fun getNote(noteId:Int): NoteModel?
    suspend fun insertNote(note: NoteModel)
    suspend fun deleteNote(note: NoteModel)
}