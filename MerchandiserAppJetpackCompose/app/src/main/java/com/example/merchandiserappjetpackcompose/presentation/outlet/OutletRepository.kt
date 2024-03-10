package com.example.merchandiserappjetpackcompose.presentation.outlet

import com.example.merchandiserappjetpackcompose.data.database.OutletDao
import com.example.merchandiserappjetpackcompose.model.Outlet
import kotlinx.coroutines.flow.Flow

class OutletRepository(private val dao: OutletDao) {

    fun getAllOutlets(): Flow<List<Outlet>> = dao.getAllOutlets()

    suspend fun insertOutlet(outlet: Outlet) {
        dao.insert(outlet)
    }

    suspend fun updateOutlet(outlet: Outlet) {
        dao.update(outlet)
    }

    suspend fun deleteOutlet(outlet: Outlet) {
        dao.delete(outlet)
    }
}