package com.example.merchandiserappjetpackcompose.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.merchandiserappjetpackcompose.data.database.RoomDatabase
import com.example.merchandiserappjetpackcompose.presentation.mainmenu.SalesNameDataStoreManager
import com.example.merchandiserappjetpackcompose.presentation.mainmenu.dataStore
import com.example.merchandiserappjetpackcompose.presentation.outlet.OutletRepository
import com.example.merchandiserappjetpackcompose.presentation.product.ProductRepository
import com.example.merchandiserappjetpackcompose.presentation.report.ReportRepository
import com.example.merchandiserappjetpackcompose.presentation.reportdetail.SellingKitProductListDataStore

class AppModuleImpl(
    private val appContext: Context,
    private val application: Application
): AppModule {

    override val database: RoomDatabase by lazy {
        Room.databaseBuilder(appContext, RoomDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    override val salesNameDataStore :SalesNameDataStoreManager by lazy {
        SalesNameDataStoreManager.getInstance(application.dataStore)
    }

    override val sellingKitProductListDataStore : SellingKitProductListDataStore by lazy {
        SellingKitProductListDataStore.getInstance(application.dataStore)
    }

    override val outletRepository: OutletRepository by lazy {
        OutletRepository(database.outletDao())
    }

    override val productRepository: ProductRepository by lazy {
        ProductRepository(database.productDao())
    }

    override val reportRepository: ReportRepository by lazy {
        ReportRepository(database.reportDao())
    }

}