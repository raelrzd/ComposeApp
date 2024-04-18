package com.alura.curso.composeapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.alura.curso.composeapp.ui.screens.HomeScreenStateHolder

class HomeScreenViewModel : ViewModel() {

    var uiStateHolder by mutableStateOf(HomeScreenStateHolder())
        private set

}