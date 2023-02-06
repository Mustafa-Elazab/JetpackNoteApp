package com.example.jetpacknoteapp.data.repository

import com.example.jetpacknoteapp.data.local.dao.NoteDao
import com.example.jetpacknoteapp.domain.model.NoteModel
import com.example.jetpacknoteapp.domain.repository.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val externalScope: CoroutineScope,
    private val ioDispatcher: CoroutineDispatcher
) : NoteRepository {
    override suspend fun getNotes(): Flow<List<NoteModel>> = withContext(ioDispatcher) {
        return@withContext  noteDao.getNotes().map { notes -> notes.map { it.toNoteModel() } }
    }

    override suspend fun searchNotes(searchWord: String): Flow<List<NoteModel>> {
        return noteDao.search(searchWord).map { notes -> notes.map { it.toNoteModel() } }
    }

    override suspend fun getNote(noteId: Int): NoteModel? {
        return noteDao.getNoteById(noteId)?.toNoteModel()
    }

    override suspend fun insertNote(note: NoteModel) {
        return externalScope.launch {
            noteDao.insertNote(note.toEntity())
        }.join()
    }

    override suspend fun deleteNote(note: NoteModel) {
        return externalScope.launch {
            noteDao.deleteNote(note.toEntity())
        }.join()
    }

}