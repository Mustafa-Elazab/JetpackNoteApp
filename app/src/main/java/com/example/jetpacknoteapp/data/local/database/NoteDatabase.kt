package com.example.jetpacknoteapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.jetpacknoteapp.data.local.dao.NoteDao
import com.example.jetpacknoteapp.data.local.entity.NoteEntity


@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao


}
