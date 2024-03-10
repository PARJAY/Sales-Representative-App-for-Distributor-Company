package com.example.merchandiserappjetpackcompose.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merchandiserappjetpackcompose.presentation.product.ProductEvent
import com.example.merchandiserappjetpackcompose.presentation.product.ProductState
import com.example.merchandiserappjetpackcompose.tools.DummyGlobalVariable
import com.example.merchandiserappjetpackcompose.ui.componenet.DialogTwoInput
import com.example.merchandiserappjetpackcompose.ui.componenet.ProductItem
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun ProductListScreen(
    productState: ProductState,
    onEvent: (ProductEvent) -> Unit
) {
    val openInputProductDialog = remember { mutableStateOf(false) }

    if (openInputProductDialog.value) DialogTwoInput(
        onDismissRequest = { openInputProductDialog.value = false },
        onConfirmation = { productName, productPrice ->
            onEvent(ProductEvent.SaveProduct(productName, productPrice))
            openInputProductDialog.value = false
        },
    )

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        FloatingActionButton(
            onClick = { openInputProductDialog.value = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    bottom = 16.dp,
                    end = 16.dp
                )
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add New Report")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Daftar Product",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(productState.products) { product ->
                // TODO : onclick dialog open and on submit database update
                ProductItem(
                    product,
                    onUpdateAction = {
                        onEvent(ProductEvent.UpdateProduct(it))
                    },
                    onDeleteAction = {
                        onEvent(ProductEvent.DeleteProduct(it))
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ProductListScreenPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            ProductListScreen(
                DummyGlobalVariable.PRODUCT_STATE,
                onEvent = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ProductListScreenNoDataPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            ProductListScreen(
                ProductState(),
                onEvent = {}
            )
        }
    }
}