package com.example.merchandiserappjetpackcompose.ui.componenet

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.merchandiserappjetpackcompose.model.Product
import com.example.merchandiserappjetpackcompose.tools.DummyGlobalVariable
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun DialogMultipleChoice(
    dialogTitle: String,
    choiceList: List<Product>,
    initialSelectedProducts : List<Product>,
    onAddNewProduct : () -> Unit,
    onConfirm: (newSelectedProducts : List<Product>) -> Unit,
    onDismiss: () -> Unit
) {
    val contex = LocalContext.current

    val selectedProductList = remember {
        mutableListOf<Product>()
    }
    selectedProductList.addAll(initialSelectedProducts)

    val newSelectedProductList = remember {
        mutableListOf<Product>()
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = dialogTitle) },

        text = {
            Column {
                Text(text = "PERINGATAN : Item yang sudah dipilih tidak dapat dibatalkan pilihannya")

                LazyColumn (Modifier.padding(top = 16.dp)) {
                    items(choiceList) { product ->
                        var isSelected by remember {
                            mutableStateOf(selectedProductList.contains(product))
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    if (!isSelected) {
                                        newSelectedProductList.add(product)
                                        isSelected = true
                                    }
                                    else Toast.makeText(contex,
                                        "Item sudah dipilih tidak dapat di non aktifkan",
                                        Toast.LENGTH_SHORT).show()
                                }
                                .background(
                                    if (isSelected) MaterialTheme.colorScheme.primaryContainer
                                    else Color.Transparent
                                )
                                .padding(16.dp)
                        ) {
                            Text(text = product.productName)
                        }
                    }
                }
            }
        },

        // TODO : kreatif tak terduga
        confirmButton = {
            RowIconWithText(
                textInformation = "Tambah barang baru",
                onClickAction = { onAddNewProduct() }
            )

            TextButton(onClick = { onDismiss() }) {
                Text("Batal")
            }

            TextButton(
                onClick = {
                    if (newSelectedProductList.isEmpty()) Toast.makeText(contex,
                        "Anda Belum Memilih Item Baru", Toast.LENGTH_SHORT
                    ).show()
                    else {
                        onDismiss()
                        onConfirm(newSelectedProductList)
                    }
                }
            ) {
                Text("Confirm")
            }
        },
    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MultipleChoiceDialogForProductSellingKitChoicePreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            val contex = LocalContext.current

            DialogMultipleChoice(
                "Edit Produk Daftar Jualan",
                DummyGlobalVariable.LIST_PRODUCT_DUMMY,
                listOf(DummyGlobalVariable.LIST_PRODUCT_DUMMY[0]),
                onAddNewProduct = {},
                onConfirm = {},
                onDismiss = {}
            )

            // TOOD : simpan sementara
//            if (isSelected) selectedProducts.remove(product)
//            else selectedProducts.add(product)
        }
    }
}