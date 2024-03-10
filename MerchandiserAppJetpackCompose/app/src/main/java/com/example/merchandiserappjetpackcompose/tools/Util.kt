package com.example.merchandiserappjetpackcompose.tools

import android.content.Context
import android.os.Environment
import android.widget.Toast
import com.example.merchandiserappjetpackcompose.model.Product
import com.example.merchandiserappjetpackcompose.model.Report
import com.opencsv.CSVWriter
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale

class Util {
    companion object {
        fun getTime() : String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

        private fun reportToCsvRow(report: Report, selectedProductList : List<Product>): Array<out String> {
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            val startTime = LocalTime.parse(report.startTime, formatter)
            val endTime = LocalTime.parse(report.endTime, formatter)
            val totalMinutes = startTime.until(endTime, ChronoUnit.MINUTES)

            val row = mutableListOf<String>()
            row.add(report.id.toString())
            row.add(report.outletName)
            row.add(report.imageTransportDistance ?: "sama dengan foto sebelumnya")
            row.add(report.imagePapOutlet)

            var totalEarning = 0.0

            selectedProductList.forEach { selectedProduct ->
                val productQuantity = report.listGoodsSold.find { it.productId == selectedProduct.id }?.productQuantity ?: 0
                row.add(productQuantity.toString())
                totalEarning += productQuantity * selectedProduct.productPrice
            }

            row.add(report.startTime)
            row.add(report.endTime)
            row.add(totalMinutes.toString())
            row.add(totalEarning.toString())

            return row.toTypedArray()
        }

        fun exportReportsToCsv(context : Context, reports: List<Report>, products : List<Product>, sellingKitProductIdList : List<Int>?) {
            val fileName = "Laporan_${SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(Date())}.csv"
            val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), fileName)

            if (reports.isEmpty()) return Toast.makeText(context, "Anda belum membuat 1 pun laporan", Toast.LENGTH_SHORT).show()
            if (sellingKitProductIdList?.isEmpty() == true) return Toast.makeText(context, "Anda belum menambahkan produk untuk dijual", Toast.LENGTH_SHORT).show()


            val headerStart = listOf("ID", "Nama Outlet", "Foto Jarak Tempuh", "Foto di Outlet",)

            // headerMiddle
            val selectedProductList = mutableListOf<Product>()

            sellingKitProductIdList?.forEach { productId ->
                products.forEach { product ->
                    if (!selectedProductList.contains(product) && productId == product.id) selectedProductList.add(product)
                }
            }

            val headerEnd = listOf("Start Time", "End Time", "Total Waktu (Menit)", "Total Keuntungan")

            val headerFix = headerStart + selectedProductList.toString() + headerEnd

            try {
                val csvWriter = CSVWriter(FileWriter(file))

                // Write header row
                csvWriter.writeNext(headerFix.toTypedArray())

                // Write data rows
                for (report in reports) {
                    csvWriter.writeNext(reportToCsvRow(report, selectedProductList))
                }

                csvWriter.close()
                Toast.makeText(context, "Laporan berhasil dibuat", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(context, "Ekspor laporan gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }
}