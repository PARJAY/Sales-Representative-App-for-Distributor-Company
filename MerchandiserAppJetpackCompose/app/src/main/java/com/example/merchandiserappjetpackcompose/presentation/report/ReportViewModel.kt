package com.example.merchandiserappjetpackcompose.presentation.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchandiserappjetpackcompose.model.Report
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ReportViewModel(
    private val reportReporsitory: ReportRepository
): ViewModel() {

    private val _state = MutableStateFlow(ReportState())

    private val _report = reportReporsitory.getAllReports().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _report) { state, report ->
            state.copy(
                reports = report
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ReportState())

    fun onEvent(event: ReportEvent) {
        when(event) {
            is ReportEvent.DeleteAllReport -> {
                viewModelScope.launch {
                    reportReporsitory.deleteAllReports()
                }
            }

            is ReportEvent.SetReportStatus -> {
                viewModelScope.launch {
                    event.report.isDisabled.not()
                    reportReporsitory.updateReport(event.report)
                }
            }

            is ReportEvent.SaveReport -> {
                val newOutlet = Report(
                    0,
                    event.outletId,
                    event.outletName,
                    event.imageTransportDistance,
                    event.imagePapOutlet,
                    event.listGoodsSold,
                    event.startTime,
                    event.endTime,
                    false
                )

                viewModelScope.launch {
                    reportReporsitory.insertReport(newOutlet)
                }
            }

        }
    }
}