package com.example.simplenote

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.simplenote.ui.home.HomeViewModel
import com.example.simplenote.ui.note.NoteViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            NoteViewModel(notesApplication().container.notesRepository)
        }

        initializer {
            HomeViewModel(notesApplication().container.notesRepository)
        }
    }
}

fun CreationExtras.notesApplication(): NotesApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as NotesApplication)