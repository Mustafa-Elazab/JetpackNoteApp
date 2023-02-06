package com.example.jetpacknoteapp.domain.usecase
import com.example.jetpacknoteapp.domain.repository.NoteRepository
import com.example.noteappcompose.domain.utilitites.InvalidNoteIdException
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(var noteRepository: NoteRepository) {
    public suspend operator fun invoke(noteId:Int) {
        if (noteId<0) {
            throw InvalidNoteIdException()
        }
        val note = noteRepository.getNote(noteId) ?: throw InvalidNoteIdException()
        noteRepository.deleteNote(note)
    }
}