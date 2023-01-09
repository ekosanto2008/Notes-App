package com.santoso.androidroom

import android.app.Application
import com.santoso.androidroom.database.remote.NoteDatabase
import com.santoso.androidroom.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication: Application() {

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { NoteDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { NoteRepository(database.noteDao()) }
}