package com.example.merchandiserappjetpackcompose.ui.componenet

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merchandiserappjetpackcompose.model.Product
import com.example.merchandiserappjetpackcompose.tools.DummyGlobalVariable
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun ProductItem(
    product: Product,
    onUpdateAction : (Product) -> Unit,
    onDeleteAction : (Product) -> Unit
    // TODO : on dialog submit, database update existing data
) {
    val openEditProductDialog = remember { mutableStateOf(false) }
    val openProductDeleteConfirmationDialog = remember { mutableStateOf(false) }

    if (openEditProductDialog.value) DialogTwoInput(
        onDismissRequest = { openEditProductDialog.value = false },
        onConfirmation = { updatedProductName, updatedProductPrice ->
            product.productName = updatedProductName
            product.productPrice = updatedProductPrice
            onUpdateAction(product)
            openEditProductDialog.value = false
        },
        product.productName,
        product.productPrice
    )

    if (openProductDeleteConfirmationDialog.value) DialogConfirmation(
        onDismissRequest = { openProductDeleteConfirmationDialog.value = false },
        onConfirmation = {
            onDeleteAction(product)
            openProductDeleteConfirmationDialog.value = false
        },
        "Product ${product.productName}"
    )

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column {
                Text(
                    text = product.productName,
                    style = MaterialTheme.typography.titleSmall,
                    fontSize = 20.sp
                )
                Text(text = product.productPrice.toString())
            }
            Row {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        openEditProductDialog.value = true
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        openProductDeleteConfirmationDialog.value = true
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ProductItemPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            ProductItem(
                DummyGlobalVariable.PRODUCT_DUMMY1,
                onUpdateAction = {},
                onDeleteAction = {},
            )
        }
    }
}