package com.example.merchandiserappjetpackcompose.presentation.report

import com.example.merchandiserappjetpackcompose.model.ProductSold
import com.example.merchandiserappjetpackcompose.model.Report

interface ReportEvent {
    data class SaveReport(
        var outletId: Int,
        var outletName: String,

        var isUsePreviousOdometerPicture: Boolean?,
        var imageTransportDistance: String?,
        var imagePapOutlet: String,

        var listGoodsSold: List<ProductSold>,
        var startTime: String,       // TODO : is data type string fix? or there are any better alternative
        var endTime: String       // TODO : is data type string fix? or there are any better alternative
    ) : ReportEvent

    // TODO : report can't be single data updated or single data deleted, there will be single data disable and delete all data instead
//    data class UpdateReport(val report: Report): ReportEvent
//    data class DeleteReport(val report: Report): ReportEvent
    data class SetReportStatus(val report: Report): ReportEvent
    object DeleteAllReport: ReportEvent
}