package com.example.simplenote

import android.app.Application
import com.example.simplenote.data.AppContainer
import com.example.simplenote.data.AppDataContainer

class NotesApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}