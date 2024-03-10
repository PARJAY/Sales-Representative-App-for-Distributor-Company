package com.example.merchandiserappjetpackcompose.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merchandiserappjetpackcompose.R
import com.example.merchandiserappjetpackcompose.presentation.product.ProductState
import com.example.merchandiserappjetpackcompose.presentation.report.ReportState
import com.example.merchandiserappjetpackcompose.tools.DummyGlobalVariable
import com.example.merchandiserappjetpackcompose.tools.Util
import com.example.merchandiserappjetpackcompose.ui.componenet.ReportItem
import com.example.merchandiserappjetpackcompose.ui.navigation.Screen
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun TodayReportScreen(
    onNavigateTo: (String) -> Unit,
    reportState: ReportState,
    productState: ProductState,
    sellingKitProductIdList : List<Int>?
) {
    val contex = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Laporan Hari Ini",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
        )

//        Row(
//            modifier = Modifier
//                .padding(8.dp)
//                .fillMaxWidth(),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text(text = "Nama Outlet")
//            Text(text = "Catatan Waktu")
//        }

        // TODO : add item click
        LazyColumn (Modifier.fillMaxWidth()) {
            items(reportState.reports) { report ->
                ReportItem(
                    report,
                    onItemSelectAction = {
                        reportState.selectedReport = it
                        onNavigateTo(Screen.ReportDetail.route)
                    }
                )
                if (report == reportState.reports.last()) Spacer(modifier = Modifier.padding(84.dp))
            }
        }
    }

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        FloatingActionButton(
            onClick = { onNavigateTo(Screen.ReportDetail.route) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    bottom = 70.dp,
                    end = 16.dp
                )
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add New Report")
        }

        Button(
            shape = RectangleShape,
            onClick = {
                Util.exportReportsToCsv(contex, reportState.reports, productState.products, sellingKitProductIdList)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Icon(ImageVector.vectorResource(R.drawable.ic_download), contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Simpan Laporan Hari ini")
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TodayReportScreenPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            TodayReportScreen(
                {},
                DummyGlobalVariable.REPORT_MINI_STATE,
                DummyGlobalVariable.PRODUCT_STATE,
                listOf()
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun TodayReportScreenNoDataPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            TodayReportScreen(
                {},
                ReportState(),
                ProductState(),
                listOf()
            )
        }
    }
}