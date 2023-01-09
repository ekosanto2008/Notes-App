package com.santoso.androidroom.repository

import com.santoso.androidroom.database.local.Note
import com.santoso.androidroom.database.remote.NoteDao
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes: Flow<List<Note>> = noteDao.getNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete() {
        noteDao.deleteAll()
    }
}