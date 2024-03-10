package com.example.merchandiserappjetpackcompose.tools

import com.example.merchandiserappjetpackcompose.model.Outlet
import com.example.merchandiserappjetpackcompose.model.Product
import com.example.merchandiserappjetpackcompose.model.ProductSold
import com.example.merchandiserappjetpackcompose.model.Report
import com.example.merchandiserappjetpackcompose.presentation.outlet.OutletState
import com.example.merchandiserappjetpackcompose.presentation.product.ProductState
import com.example.merchandiserappjetpackcompose.presentation.report.ReportState

// TODO : REPLACE LATER
class DummyGlobalVariable {

    companion object {
        // INDIVIDUAL MODEL DUMMY //
        // INDIVIDUAL MODEL DUMMY //
        // INDIVIDUAL MODEL DUMMY //

        val OUTLET_DUMMY_1 = Outlet(-1, "Singaraja")
        val OUTLET_DUMMY_2 = Outlet(-2, "Singapadu")
        val OUTLET_DUMMY_3 = Outlet(-3, "Kuta")
        val OUTLET_DUMMY_4 = Outlet(-4, "Mengwi")
        val OUTLET_DUMMY_5 = Outlet(-5, "Sanur")

        val PRODUCT1_DUMMY = Product(-1, "Kopi Kapal Api", 5000.0)

        val PRODUCT_SOLD_DUMMY = ProductSold(-1, 5  )

        val REPORT_DUMMY = Report(
            -1,
            -1,
            "Singaraja",
            "uri to image",
            "uri to image",
            arrayListOf(PRODUCT_SOLD_DUMMY),
            "10:55",
            "11:00"
        )

        val REPORT_LAST_DUMMY = Report(
            -1,
            -1,
            "Singapadu",
            "uri to image",
            "uri to image",
            arrayListOf(PRODUCT_SOLD_DUMMY),
            "10:55",
            "11:00"
        )

        // INDIVIDUAL MODEL DUMMY //
        // INDIVIDUAL MODEL DUMMY //
        // INDIVIDUAL MODEL DUMMY //



        // LIST MODEL DUMMY //
        // LIST MODEL DUMMY //
        // LIST MODEL DUMMY //

        val LIST_MINI_REPORT_DUMMY = arrayListOf(
            REPORT_DUMMY,
            REPORT_LAST_DUMMY,
        )

        val LIST_REPORT_DUMMY = arrayListOf(
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_DUMMY,
            REPORT_LAST_DUMMY,
        )


        val LIST_OUTLET_DUMMY = arrayListOf(
            OUTLET_DUMMY_1,
            OUTLET_DUMMY_2,
            OUTLET_DUMMY_3,
            OUTLET_DUMMY_4,
            OUTLET_DUMMY_5,
        )

        val PRODUCT_DUMMY1 = Product(0, "Kopi XYZ", 5000.0)
        val PRODUCT_DUMMY2 = Product(0, "Kopi Pesawat Api", 2500.0)
        val PRODUCT_DUMMY3 = Product(0, "Luwak Dark Kopi", 7500.0)
        val PRODUCT_DUMMY4 = Product(0, "Luwak Brown Kopi", 10000.0)
        val PRODUCT_DUMMY5 = Product(0, "Great Day Coffe Bottle", 7500.0)

        val LIST_PRODUCT_DUMMY = arrayListOf(
            PRODUCT_DUMMY1,
            PRODUCT_DUMMY2,
            PRODUCT_DUMMY3,
            PRODUCT_DUMMY4,
            PRODUCT_DUMMY5,
        )

        val LIST_PRODUCT_DUMMY_2 = arrayListOf(
            PRODUCT_DUMMY1,
            PRODUCT_DUMMY2,
            PRODUCT_DUMMY3,
            PRODUCT_DUMMY1,
            PRODUCT_DUMMY2,
            PRODUCT_DUMMY3,
            PRODUCT_DUMMY1,
            PRODUCT_DUMMY2,
            PRODUCT_DUMMY3,
        )

        // LIST MODEL DUMMY //
        // LIST MODEL DUMMY //
        // LIST MODEL DUMMY //



        // STATE //
        // STATE //
        // STATE //
        // STATE //
        val PRODUCT_STATE = ProductState(
            LIST_PRODUCT_DUMMY,
        )

        val OUTLET_STATE = OutletState(
            LIST_OUTLET_DUMMY
        )

        val REPORT_MINI_STATE = ReportState(
            LIST_MINI_REPORT_DUMMY
        )

        val REPORT_STATE = ReportState(
            LIST_REPORT_DUMMY
        )

        // PREVIEW SCREEN //
        // PREVIEW SCREEN //
        // PREVIEW SCREEN //
    }
}