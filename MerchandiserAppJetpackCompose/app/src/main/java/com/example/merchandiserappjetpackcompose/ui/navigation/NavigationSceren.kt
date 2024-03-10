package com.example.merchandiserappjetpackcompose.ui.navigation

import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.merchandiserappjetpackcompose.MyApp
import com.example.merchandiserappjetpackcompose.model.Product
import com.example.merchandiserappjetpackcompose.model.ProductSold
import com.example.merchandiserappjetpackcompose.presentation.feature.EnumPhotoCapture
import com.example.merchandiserappjetpackcompose.presentation.feature.createImageFile
import com.example.merchandiserappjetpackcompose.presentation.mainmenu.SalesNameViewModel
import com.example.merchandiserappjetpackcompose.presentation.outlet.OutletState
import com.example.merchandiserappjetpackcompose.presentation.outlet.OutletViewModel
import com.example.merchandiserappjetpackcompose.presentation.outlet.ProductViewModel
import com.example.merchandiserappjetpackcompose.presentation.product.ProductState
import com.example.merchandiserappjetpackcompose.presentation.report.ReportState
import com.example.merchandiserappjetpackcompose.presentation.report.ReportViewModel
import com.example.merchandiserappjetpackcompose.presentation.reportdetail.SellingKitProductListViewModel
import com.example.merchandiserappjetpackcompose.tools.Util
import com.example.merchandiserappjetpackcompose.ui.screen.MainMenuScreen
import com.example.merchandiserappjetpackcompose.ui.screen.OutletListScreen
import com.example.merchandiserappjetpackcompose.ui.screen.ProductListScreen
import com.example.merchandiserappjetpackcompose.ui.screen.ReportDetailScreen
import com.example.merchandiserappjetpackcompose.ui.screen.TakePicture
import com.example.merchandiserappjetpackcompose.ui.screen.TodayReportScreen
import com.plcoding.manualdependencyinjection.presentation.viewModelFactory
import java.util.Objects

@Composable
fun NavigationSceren(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    reportVM: ReportViewModel = viewModel(
        factory = viewModelFactory {
            ReportViewModel(MyApp.appModule.reportRepository)
        }
    ),
) {
    val context = LocalContext.current

    var whichImage by remember { mutableStateOf(EnumPhotoCapture.NOT_YET_SETTED) }

    var uriImageHandler : Uri = Uri.EMPTY

    var capturedImageOutletUri by remember { mutableStateOf<Uri>(Uri.EMPTY) }
    var capturedImageOdometerUri by remember { mutableStateOf<Uri>(Uri.EMPTY) }

    val reportState by reportVM.state.collectAsState(initial = ReportState())

    NavHost(
        navController = navController,
        startDestination = Screen.MainMenu.route
    ) {
        composable(route = Screen.MainMenu.route) {

            @Composable
            fun NestedMainMenuScreen(
                salesNameVM: SalesNameViewModel = viewModel(
                    factory = viewModelFactory {
                        SalesNameViewModel(MyApp.appModule.salesNameDataStore)
                    }
                )
            ) {
                val salesName by salesNameVM.salesName.collectAsState()

                MainMenuScreen(
                    onNavigateTo =  { navController.navigate(it) },
                    changeSalesRepresentativeName = { salesNameVM.saveUsername(it) },
                    salesName
                )
            }

            NestedMainMenuScreen()
        }

        // Note : no nested function needed
        composable(Screen.TodayReport.route) {
            if (reportState.startTime != "") reportState.startTime = ""

            @Composable
            fun NestedTodayReportScreen(
                productVM: ProductViewModel = viewModel(
                    factory = viewModelFactory {
                        ProductViewModel(MyApp.appModule.productRepository)
                    }
                ),
                sellingKitProductListVM : SellingKitProductListViewModel = viewModel(
                    factory = viewModelFactory {
                        SellingKitProductListViewModel(MyApp.appModule.sellingKitProductListDataStore)
                    }
                )
            ) {
                val sellingKitProductIdList by sellingKitProductListVM.sellingProductList.collectAsState()
                val productState by productVM.state.collectAsState(initial = ProductState())

                TodayReportScreen(
                    onNavigateTo =  { navController.navigate(it) },
                    reportState,
                    productState,
                    sellingKitProductIdList
                )
            }
        }

        // TODO : Figure out how preview mechanic work
        composable(Screen.ReportDetail.route) {
            if (reportState.startTime == "") reportState.startTime = Util.getTime()

            @Composable
            fun NestedReportDetailScreen(
                outletVM: OutletViewModel = viewModel(
                    factory = viewModelFactory {
                        OutletViewModel(MyApp.appModule.outletRepository)
                    }
                ),
                productVM: ProductViewModel = viewModel(
                    factory = viewModelFactory {
                        ProductViewModel(MyApp.appModule.productRepository)
                    }
                ),
                sellingKitProductListVM : SellingKitProductListViewModel = viewModel(
                    factory = viewModelFactory {
                        SellingKitProductListViewModel(MyApp.appModule.sellingKitProductListDataStore)
                    }
                )
            ) {
                val sellingKitProductList by sellingKitProductListVM.sellingProductList.collectAsState()
                val selectedProducts = mutableListOf<Product>()

                val outletState by outletVM.state.collectAsState(initial = OutletState())
                val productState by productVM.state.collectAsState(initial = ProductState())

                sellingKitProductList?.forEach { productId ->
                    productState.products.forEach { product ->
                        if (!selectedProducts.contains(product) && productId == product.id) selectedProducts.add(product)
                    }
                }

                for (selectedProduct in selectedProducts) {
                    val productId = selectedProduct.id
                    val existingProductSold = reportState.listGoodsSold.find { it.productId == productId }

                    if (existingProductSold == null)
                        reportState.listGoodsSold.add(ProductSold(productId = productId))
                }

                ReportDetailScreen(
                    outletState,
                    outletVM::onEvent,
                    productState,
                    productVM::onEvent,
                    reportState,
                    reportVM::onEvent,

                    selectedProducts,
                    sellingKitProductUpdate = { newSelectedProductListId ->
                        newSelectedProductListId.forEach { newSelectedProductId ->
                            sellingKitProductListVM.addItem(newSelectedProductId)
                        }
                    },

                    capturedImageOutletUri,
                    capturedImageOdometerUri,

                    onNavigateTo =  { navController.navigate(it) },
                    onReportCreated = {
                        // reset all the reportState data
                        reportState.outletId = -1
                        reportState.outletName = "Silahkan Pilih Outlet"
                        reportState.imageTransportDistance = ""
                        reportState.isUsePreviousOdometerPicture = false
                        reportState.imagePapOutlet = ""
                        reportState.listGoodsSold = arrayListOf()
                        reportState.selectedReport = null
                        reportState.startTime = ""

                        // TODO : on sucess pop up back stack
                        // TODO : testing by clicking back button
                        navController.popBackStack()
                    },
                    setCaptureImage = {
                        uriImageHandler = FileProvider.getUriForFile(
                            Objects.requireNonNull(context),
                            context.packageName + ".provider", context.createImageFile(whichImage)
                        )
                        whichImage = it
                    }
                )
            }

            NestedReportDetailScreen()
        }

        // TODO : not yet Implemented
        composable(Screen.ReportDetailReview.route) {
//            ReportDetailReviewScreen(
//                //    reviewedReportData : Report?,
//            )
        }

        composable(Screen.OutletList.route) {
            @Composable
            fun NestedOutletListScreen(
                outletVM: OutletViewModel = viewModel(
                    factory = viewModelFactory {
                        OutletViewModel(MyApp.appModule.outletRepository)
                    }
                )
            ) {
                val outletState by outletVM.state.collectAsState(initial = OutletState())
                OutletListScreen(outletState, outletVM::onEvent)
            }

            NestedOutletListScreen()
        }

        composable(Screen.ProductList.route) {
            @Composable
            fun NestedProductListScreen(
                productVM: ProductViewModel = viewModel(
                    factory = viewModelFactory {
                        ProductViewModel(MyApp.appModule.productRepository)
                    }
                )
            ) {
                val productState by productVM.state.collectAsState(initial = ProductState())
                ProductListScreen(productState, productVM::onEvent)
            }

            NestedProductListScreen()
        }

        composable(Screen.TakePicture.route) {
            TakePicture(
                uriImageHandler = uriImageHandler,
                onImageCaptured = { isPhotoTakem, uriPhotoTaken ->
                    if (isPhotoTakem) when (whichImage) {
                        EnumPhotoCapture.OUTLET -> capturedImageOutletUri = uriImageHandler
                        EnumPhotoCapture.ODOMETER -> capturedImageOdometerUri = uriImageHandler
                        else -> Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT).show()
                    }
                    navController.popBackStack()
                }
            )
        }
    }
}