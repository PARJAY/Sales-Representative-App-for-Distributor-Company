package com.example.merchandiserappjetpackcompose.presentation.outlet

import com.example.merchandiserappjetpackcompose.model.Outlet

sealed interface OutletEvent {
    data class SaveOutlet(val outletName: String): OutletEvent
    data class UpdateOutlet(val outlet: Outlet): OutletEvent
    data class DeleteOutlet(val outlet: Outlet): OutletEvent
}