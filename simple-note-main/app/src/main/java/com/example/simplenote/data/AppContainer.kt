package com.example.simplenote.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val notesRepository: NotesRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineNotesRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [NotesRepository]
     */
    override val notesRepository: NotesRepository by lazy {
        OfflineNotesRepository(NoteBookDatabase.getDatabase(context).noteDao())
    }
}