package com.example.simplenote.ui.note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.simplenote.data.NotesRepository
import com.example.simplenote.data.Note

class NoteViewModel(private val notesRepository: NotesRepository) : ViewModel() {
    var title by mutableStateOf("")
        private set

    var content by mutableStateOf("")
        private set

    fun onTitleChange(newTitle: String) {
        title = newTitle
    }

    fun onContentChange(newContent: String) {
        content = newContent
    }

    suspend fun saveNote() {
        notesRepository.insertNote(
            Note(
                title = title,
                description = content
            )
        )
    }
}
