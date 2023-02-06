package com.example.jetpacknoteapp.domain.usecase

import com.example.jetpacknoteapp.domain.model.NoteModel
import com.example.jetpacknoteapp.domain.repository.NoteRepository

import com.example.noteappcompose.domain.utilitites.InvalidNoteSubtitleException
import com.example.noteappcompose.domain.utilitites.InvalidNoteTextException
import com.example.noteappcompose.domain.utilitites.InvalidNoteTitleException
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(var noteRepository: NoteRepository) {

     suspend operator fun invoke(note: NoteModel) {
        if (note.noteTitle.isBlank()) {
            throw InvalidNoteTitleException()
        }
        if (note.noteSubtitle.isBlank()) {
            throw InvalidNoteSubtitleException()
        }
        if (note.noteText.isBlank()) {
            throw InvalidNoteTextException()
        }
        noteRepository.insertNote(note)
    }
}