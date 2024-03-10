package com.example.merchandiserappjetpackcompose.ui.screen

import android.content.res.Configuration
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merchandiserappjetpackcompose.model.Product
import com.example.merchandiserappjetpackcompose.model.ProductSold
import com.example.merchandiserappjetpackcompose.presentation.feature.EnumPhotoCapture
import com.example.merchandiserappjetpackcompose.presentation.outlet.OutletEvent
import com.example.merchandiserappjetpackcompose.presentation.outlet.OutletState
import com.example.merchandiserappjetpackcompose.presentation.product.ProductEvent
import com.example.merchandiserappjetpackcompose.presentation.product.ProductState
import com.example.merchandiserappjetpackcompose.presentation.report.ReportEvent
import com.example.merchandiserappjetpackcompose.presentation.report.ReportState
import com.example.merchandiserappjetpackcompose.tools.DummyGlobalVariable
import com.example.merchandiserappjetpackcompose.tools.Util.Companion.getTime
import com.example.merchandiserappjetpackcompose.ui.componenet.CardTakePicture
import com.example.merchandiserappjetpackcompose.ui.componenet.DialogMultipleChoice
import com.example.merchandiserappjetpackcompose.ui.componenet.DialogOneInput
import com.example.merchandiserappjetpackcompose.ui.componenet.DialogTwoInput
import com.example.merchandiserappjetpackcompose.ui.componenet.DropDownOutlineText
import com.example.merchandiserappjetpackcompose.ui.componenet.ImageDisplayer
import com.example.merchandiserappjetpackcompose.ui.componenet.RowIconWithText
import com.example.merchandiserappjetpackcompose.ui.componenet.logic.EnumDialogRequirment
import com.example.merchandiserappjetpackcompose.ui.navigation.Screen
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun ReportDetailScreen(
    // outlet state + event
    outletState: OutletState,
    onOutletEvent: (OutletEvent) -> Unit,

    // product state + event
    productState: ProductState,
    onProductEvent: (ProductEvent) -> Unit,

    // report state + event
    reportState: ReportState,
    onReportEvent: (ReportEvent) -> Unit,

    // selling data
    sellingKitProductList : List<Product> = emptyList(),        // historical note : this "= emptyList()" declaration is needed
    sellingKitProductUpdate : (List<Int>) -> Unit,

    // picture data
    outletPicture: Uri,
    odometerPicture: Uri,

    onNavigateTo: (String) -> Unit,
    onReportCreated: () -> Unit,
    setCaptureImage: (EnumPhotoCapture) -> Unit,
) {
    val contex = LocalContext.current

    // dialog area
    val openInputOutletDialog = remember { mutableStateOf(false) }
    if (openInputOutletDialog.value) DialogOneInput(
        onDismissRequest = { openInputOutletDialog.value = false },
        onConfirmation = {
            onOutletEvent(OutletEvent.SaveOutlet(it))
            openInputOutletDialog.value = false
        },
        EnumDialogRequirment.INPUT_OUTLET
    )

    val openInputProductDialog = remember { mutableStateOf(false) }
    if (openInputProductDialog.value) DialogTwoInput(
        onDismissRequest = { openInputProductDialog.value = false },
        onConfirmation = { productName, productPrice ->
            onProductEvent(ProductEvent.SaveProduct(productName, productPrice))
            openInputProductDialog.value = false
        },
    )

    val openDialogMultipleChoice = remember { mutableStateOf(false) }
    if (openDialogMultipleChoice.value) DialogMultipleChoice(
        dialogTitle = "Edit Produk Daftar Jualan",
        choiceList = productState.products,
        initialSelectedProducts = sellingKitProductList,
        onDismiss = { openDialogMultipleChoice.value = false },
        onAddNewProduct = { openInputProductDialog.value = true },
        onConfirm = { newSelectedProducts ->
            val newSelectedProductsId = mutableListOf<Int>()
            newSelectedProducts.forEach { selectedProducts ->
                newSelectedProductsId.add(selectedProducts.id)
            }
            sellingKitProductUpdate(newSelectedProductsId)      // TODO : save the new value
        },
    )

    // report variable area

    // TODO : potential redudancy with 2 container var
    var selectedOutletId by remember { mutableIntStateOf(reportState.outletId) }
    var selectedOutletName by remember { mutableStateOf(reportState.outletName) }

    val sellingKitProductListQuantity = remember { mutableStateListOf<Int>() }  // SOLVED : real time update have to use mutableStateListOf

    if (sellingKitProductList.isNotEmpty()) {
        sellingKitProductListQuantity.clear()
        sellingKitProductListQuantity.addAll(List(sellingKitProductList.size) { 0 }) // Initialize with 0s

        reportState.listGoodsSold.forEachIndexed { i, it ->
            sellingKitProductListQuantity[i] = it.productQuantity
        }
    }

    var isUsePreviousOdometerPicture by remember { mutableStateOf(false) }

    val productSoldList = remember { mutableStateListOf<ProductSold>() }

    val MAKE_REPORT_BUTTON_HEIGHT = 56
    val ROW_IMAGE_CONTAINER_HEIGHT = 210

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Laporan Kunjungan Outlet",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            lineHeight = 48.sp,
            modifier = Modifier.padding(top = 32.dp),
        )

        Spacer(modifier = Modifier.padding(vertical = 12.dp))

        // TODO : potential redudancy with 2 container var
        DropDownOutlineText (
            selectedOutletName,
            outletState.outlets,
            onItemSelected = {
                reportState.outletName = it.outletName
                reportState.outletId = it.id
                selectedOutletName = it.outletName
                selectedOutletId = it.id
            }
        )

        RowIconWithText(
            textInformation = "Tambah outlet baru",
            onClickAction = { openInputOutletDialog.value = true }
        )

        Spacer(modifier = Modifier.padding(vertical = 12.dp))

        Text(
            text = "Daftar Barang Jualan",
            style = MaterialTheme.typography.titleSmall,
            fontSize = 20.sp
        )

        // HISTORICAL NOTE : I was invented my own original new problem here with
        // if (index == 0) display content header
        // if (index == last) display content footer

        // HISTORICAL NOTE UPDATE : well, I think I found a special case in Lazy Column init the Lazy
        // Column first with empty list so, the device can estimate the size
        // so, the component below then populate the data later

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(sellingKitProductList) { product ->
                val index = sellingKitProductList.indexOf(product) // Ensure correct index

                // SOLVED : doesnt update in real time (check val sellingKitProductListQuantity for more detail)
                OutlinedTextField(
                    value = sellingKitProductListQuantity[index].toString(),
                    onValueChange = { newValue ->
                        if (newValue.isNotBlank()) {
                            reportState.listGoodsSold[index].productQuantity = newValue.toInt()
//                            sellingKitProductListQuantity[index] = newValue.toInt()
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(product.productName) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        RowIconWithText(
            "Edit Produk Daftar Jualan",
            onClickAction = { openDialogMultipleChoice.value = true },
            Icons.Default.Edit
        )

        Spacer(modifier = Modifier.padding(bottom = (MAKE_REPORT_BUTTON_HEIGHT + ROW_IMAGE_CONTAINER_HEIGHT).dp))
    }


    // sticky bottom design
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .height(ROW_IMAGE_CONTAINER_HEIGHT.dp)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column (horizontalAlignment = Alignment.CenterHorizontally) {
                if (odometerPicture.path?.isEmpty() == true)
                    CardTakePicture(
                        supportingText = "Ambil Foto Odometer",
                        modifier = Modifier.clickable {
                            setCaptureImage(EnumPhotoCapture.ODOMETER)
                            onNavigateTo(Screen.TakePicture.route)
                        }
                    )
                else
                    ImageDisplayer(
                        "Ambil Foto Odometer",
                        odometerPicture,
                        onClick = {
                            setCaptureImage(EnumPhotoCapture.ODOMETER)
                            onNavigateTo(Screen.TakePicture.route)
                        }
                    )
                Row(
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Checkbox(
                        checked = isUsePreviousOdometerPicture,
                        onCheckedChange = { isUsePreviousOdometerPicture = isUsePreviousOdometerPicture.not() },
                        Modifier.size(20.dp)
                    )
                    Text(
                        fontSize = 12.sp,
                        text = "Gunakan foto sebelumnya",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Column (
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (outletPicture.path?.isEmpty() == true)
                    CardTakePicture(
                        supportingText = "Ambil Foto Outlet",
                        modifier = Modifier.clickable {
                            setCaptureImage(EnumPhotoCapture.OUTLET)
                            onNavigateTo(Screen.TakePicture.route)
                        }
                    )
                else
                    ImageDisplayer(
                        "Ambil Foto Outlet",
                        outletPicture,
                        onClick = {
                            setCaptureImage(EnumPhotoCapture.OUTLET)
                            onNavigateTo(Screen.TakePicture.route)
                        }
                    )
            }
        }

        Button(
            shape = RectangleShape,
            onClick = {

                // check if all item is filled

                if (selectedOutletId == -1 || selectedOutletName == "Silahkan Pilih Outlet") {
                    Toast.makeText(contex, "Outlet Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                // Daftar Barang Jualan Boleh Kosong (misal udah mampir ke outlet tapi outletnya tutup)

                if (odometerPicture.path.isNullOrEmpty() && !isUsePreviousOdometerPicture) {
                    Toast.makeText(contex, "Foto atau Keterangan Odometer Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (outletPicture.path.isNullOrEmpty()) {
                    Toast.makeText(contex, "Foto Outlet Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                productSoldList.clear() // Ensure it's initially empty
                productSoldList.addAll(List(sellingKitProductList.size) { ProductSold(-1, 0) })

                sellingKitProductList.forEachIndexed { index, sellingKitProduct ->
                    productSoldList[index].productId = sellingKitProduct.id
                    productSoldList[index].productQuantity = sellingKitProductListQuantity[index]
                }

                // Test -> SUCCESS
                onReportEvent(
                    ReportEvent.SaveReport(
                        selectedOutletId,
                        selectedOutletName,
                        isUsePreviousOdometerPicture,
                        odometerPicture.path.toString(),
                        outletPicture.path.toString(),
                        productSoldList,
                        startTime =  reportState.startTime,
                        endTime = getTime()
                    )
                )

                onReportCreated()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(MAKE_REPORT_BUTTON_HEIGHT.dp)
        ) {
            Text("Buat Laporan")
        }
    }
}

// create new report data
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ReportDetailScreenCreateNewReportPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            val capturedImageUri by remember {
                mutableStateOf<Uri>(Uri.EMPTY)
            }

            ReportDetailScreen(
                outletState = DummyGlobalVariable.OUTLET_STATE,
                onOutletEvent = {},
                productState = DummyGlobalVariable.PRODUCT_STATE,
                onProductEvent = {},
                reportState = DummyGlobalVariable.REPORT_STATE,
                onReportEvent = {},

                sellingKitProductList = emptyList(),
                sellingKitProductUpdate = {},
                capturedImageUri,
                capturedImageUri,

                onNavigateTo = {},
                onReportCreated = {},
                setCaptureImage = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ReportDetailScreenInjectedSellingKitProductListCreateNewReportPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            val capturedImageUri by remember {
                mutableStateOf<Uri>(Uri.EMPTY)
            }

            val selectedProducts = mutableListOf<Product>()
            selectedProducts.addAll(DummyGlobalVariable.LIST_PRODUCT_DUMMY)

            ReportDetailScreen(
                outletState = DummyGlobalVariable.OUTLET_STATE,
                onOutletEvent = {},
                productState = DummyGlobalVariable.PRODUCT_STATE,
                onProductEvent = {},
                reportState = DummyGlobalVariable.REPORT_STATE,
                onReportEvent = {},

                sellingKitProductList = selectedProducts,
                sellingKitProductUpdate = {},
                capturedImageUri,
                capturedImageUri,

                onNavigateTo = {},
                onReportCreated = {},
                setCaptureImage = {},
            )
        }
    }
}