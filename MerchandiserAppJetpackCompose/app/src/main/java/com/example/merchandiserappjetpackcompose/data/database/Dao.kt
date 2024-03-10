package com.example.merchandiserappjetpackcompose.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.merchandiserappjetpackcompose.model.Outlet
import com.example.merchandiserappjetpackcompose.model.Product
import com.example.merchandiserappjetpackcompose.model.Report
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(report: Report)

    @Update
    suspend fun update(report: Report)

    @Delete
    suspend fun delete(report: Report)

    @Query("DELETE FROM reports")
    suspend fun deleteAllReports()

    @Query("SELECT * FROM reports WHERE id = :id")
    suspend fun getReportById(id: Long): Report

    @Query("SELECT * FROM reports")
    fun getAllReports(): Flow<List<Report>>
}

@Dao
interface OutletDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(outlet: Outlet)

    @Update
    suspend fun update(outlet: Outlet)

    @Delete
    suspend fun delete(outlet: Outlet)

    @Query("SELECT * FROM outlet WHERE id = :id")
    suspend fun getOutletById(id: Long): Outlet

    @Query("SELECT * FROM outlet")
    fun getAllOutlets(): Flow<List<Outlet>>
}

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(goods: Product)

    @Update
    suspend fun update(goods: Product)

    @Delete
    suspend fun delete(goods: Product)

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun getProductById(id: Long): Product

    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<Product>>
}