package com.example.merchandiserappjetpackcompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.merchandiserappjetpackcompose.model.Outlet
import com.example.merchandiserappjetpackcompose.model.Product
import com.example.merchandiserappjetpackcompose.model.Report

@Database(entities = [Report::class, Outlet::class, Product::class], version = 1)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun reportDao(): ReportDao
    abstract fun outletDao(): OutletDao
    abstract fun productDao(): ProductDao
}
