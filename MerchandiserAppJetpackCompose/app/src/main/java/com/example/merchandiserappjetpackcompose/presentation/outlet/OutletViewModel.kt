package com.example.merchandiserappjetpackcompose.presentation.outlet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchandiserappjetpackcompose.model.Outlet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OutletViewModel(
    private val outletRepository: OutletRepository
): ViewModel() {

    private val _state = MutableStateFlow(OutletState())

    private val _outlet = outletRepository.getAllOutlets().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _outlet) { state, outlet ->
            state.copy(
                outlets = outlet
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), OutletState())

    fun onEvent(event: OutletEvent) {
        when(event) {
            is OutletEvent.DeleteOutlet -> {
                viewModelScope.launch {
                    outletRepository.deleteOutlet(event.outlet)
                }
            }

            is OutletEvent.UpdateOutlet -> {
                viewModelScope.launch {
                    outletRepository.updateOutlet(event.outlet)
                }
            }

            is OutletEvent.SaveOutlet -> {
                val newOutlet = Outlet(outletName = event.outletName)
                viewModelScope.launch {
                    outletRepository.insertOutlet(newOutlet)
                }
            }
        }
    }
}