package com.example.merchandiserappjetpackcompose.ui.componenet

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme


// TODO : ujung-ujungnya tidak terpakai
@Composable
fun ModifiedEditText(
    textLabel: String,
    value : Int,
    onValueChange : (Int) -> Unit,
    openDialogChangeProduct : () -> Unit
) {
    OutlinedTextField(
        value = value.toString(),
        onValueChange = { onValueChange(it.toInt()) },
        label = { Text(textLabel) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Icon Change Product Selection",
                modifier = Modifier.clickable {
                    openDialogChangeProduct()
                }
            )
        }
    )

    Box (
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        DeleteIcon(
            cancleProductSelection = {}
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ModifiedEditTextPreview() {
    MerchandiserAppJetpackComposeTheme {
        var productCount by remember {
            mutableIntStateOf(0)
        }

        Surface {
            ModifiedEditText(
                textLabel = "Produk 1",
                productCount,
                onValueChange = {
                    productCount = it
                },
                openDialogChangeProduct = {}
            )
        }
    }
}