package com.example.merchandiserappjetpackcompose.presentation.mainmenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SalesNameViewModel(private val dataStore: SalesNameDataStoreManager) : ViewModel() {

    val salesName: StateFlow<String> = dataStore.getUsername().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ""
    )

    fun saveUsername(username: String) {
        viewModelScope.launch {
            dataStore.saveUsername(username)
        }
    }
}