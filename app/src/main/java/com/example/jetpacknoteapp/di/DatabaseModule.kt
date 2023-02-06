package com.example.jetpacknoteapp.di

import android.app.Application
import androidx.room.Room
import com.example.jetpacknoteapp.data.local.dao.NoteDao
import com.example.jetpacknoteapp.data.local.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(app: Application): NotesDatabase {
        return Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            "note_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(notesDatabase: NotesDatabase): NoteDao {
        return notesDatabase.noteDao()
    }


}