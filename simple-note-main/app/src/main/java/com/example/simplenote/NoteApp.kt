package com.example.simplenote

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplenote.ui.home.HomeScreen
import com.example.simplenote.ui.home.HomeViewModel
import com.example.simplenote.ui.note.NoteScreen
import com.example.simplenote.ui.note.NoteViewModel
import kotlinx.coroutines.launch

enum class Screen {
    HomeScreen,
    NoteScreen
}

@Composable
fun NoteApp() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.name) {

        composable(Screen.HomeScreen.name) {
            val homeViewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
            val homeUiState by homeViewModel.homeUiState.collectAsState()

            HomeScreen(
                notes = homeUiState.noteList,
                onNoteClick = { note ->
                    navController.navigate("${Screen.NoteScreen.name}/${note.id}")
                },
                onAddClick = {
                    navController.navigate(Screen.NoteScreen.name)
                },
                onSettingsClick = {
                    // Implement settings navigation if needed
                }
            )
        }

        composable(Screen.NoteScreen.name) {
            val noteViewModel: NoteViewModel = viewModel(factory = AppViewModelProvider.Factory)

            NoteScreen(
                title = noteViewModel.title,
                content = noteViewModel.content,
                onTitleChange = noteViewModel::onTitleChange,
                onContentChange = noteViewModel::onContentChange,
                onBackClick = {
                    coroutineScope.launch {
                        noteViewModel.saveNote()
                        navController.popBackStack()
                    }
                },
                onDeleteClick = {}
            )
        }
    }
}
