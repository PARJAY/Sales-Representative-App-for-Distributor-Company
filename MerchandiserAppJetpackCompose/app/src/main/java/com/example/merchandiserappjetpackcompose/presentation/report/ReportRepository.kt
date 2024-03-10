package com.example.merchandiserappjetpackcompose.presentation.report

import com.example.merchandiserappjetpackcompose.data.database.ReportDao
import com.example.merchandiserappjetpackcompose.model.Report
import kotlinx.coroutines.flow.Flow

class ReportRepository(private val dao: ReportDao) {

    fun getAllReports(): Flow<List<Report>> = dao.getAllReports()

    suspend fun insertReport(report: Report) {
        dao.insert(report)
    }

    suspend fun updateReport(report: Report) {
        dao.update(report)
    }

    suspend fun deleteAllReports() {
        dao.deleteAllReports()
    }
}