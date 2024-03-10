package com.example.merchandiserappjetpackcompose.di

import com.example.merchandiserappjetpackcompose.data.database.RoomDatabase
import com.example.merchandiserappjetpackcompose.presentation.mainmenu.SalesNameDataStoreManager
import com.example.merchandiserappjetpackcompose.presentation.outlet.OutletRepository
import com.example.merchandiserappjetpackcompose.presentation.product.ProductRepository
import com.example.merchandiserappjetpackcompose.presentation.report.ReportRepository
import com.example.merchandiserappjetpackcompose.presentation.reportdetail.SellingKitProductListDataStore

interface AppModule {
    val database: RoomDatabase
    val salesNameDataStore : SalesNameDataStoreManager
    val sellingKitProductListDataStore : SellingKitProductListDataStore
    val outletRepository : OutletRepository
    val productRepository : ProductRepository
    val reportRepository : ReportRepository
}

