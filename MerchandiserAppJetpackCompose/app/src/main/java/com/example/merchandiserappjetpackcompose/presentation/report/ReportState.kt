package com.example.merchandiserappjetpackcompose.presentation.report

import com.example.merchandiserappjetpackcompose.model.ProductSold
import com.example.merchandiserappjetpackcompose.model.Report

data class ReportState(
    val reports: List<Report> = emptyList(),

    // container
    var outletId: Int = -1,
    var outletName: String = "Silahkan Pilih Outlet",

    var imageTransportDistance: String = "",
    var isUsePreviousOdometerPicture: Boolean = false,

    var imagePapOutlet: String = "",

    var listGoodsSold: ArrayList<ProductSold> = arrayListOf(),

    var selectedReport: Report? = null,

    var startTime: String = ""
)