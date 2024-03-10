package com.example.merchandiserappjetpackcompose.ui.componenet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merchandiserappjetpackcompose.model.Product

@Composable
fun CheckboxDialog(
    products: List<Product>,
    onSelectionChanged: (List<Product>) -> Unit,
    onDismiss: () -> Unit
) {
    val selectedProducts = remember { mutableStateOf(products.toMutableList()) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Products") },
        text = {
            Column {
                products.forEach { product ->
                    Row {
                        Checkbox(
                            checked = selectedProducts.value.contains(product),
                            onCheckedChange = { checked ->
                                val updatedList = selectedProducts.value.toMutableList()

                                if (checked) updatedList.add(product)
                                else updatedList.remove(product)

                                selectedProducts.value = updatedList
                            },
                        )

                        Text(
                            fontSize = 12.sp,
                            text = product.productName,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = { onSelectionChanged(selectedProducts.value) }) {
                Text("Select")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}