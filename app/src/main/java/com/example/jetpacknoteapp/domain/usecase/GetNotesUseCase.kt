package com.example.jetpacknoteapp.domain.usecase
import com.example.jetpacknoteapp.domain.model.NoteModel
import com.example.jetpacknoteapp.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(var noteRepository: NoteRepository) {
    suspend operator fun invoke(): Flow<List<NoteModel>> {
        return noteRepository.getNotes().map { notes ->
            notes.sortedBy {
                val sdf = SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm")
                sdf.parse(it.dateTime)?.time
            }
        }
    }
}