package com.example.merchandiserappjetpackcompose.ui.navigation

sealed class Screen(val route: String) {
    // displayed "Home" but techically "ArtListScreen"

    data object MainMenu : Screen("MainMenu")
    data object TodayReport : Screen("TodayReport")

    data object ReportDetail : Screen("ReportDetail")
    data object ReportDetailReview : Screen("ReportDetailReview")
    data object OutletList : Screen("OutletList")
    data object ProductList : Screen("ProductList")
    data object TakePicture : Screen("TakePicture")
}