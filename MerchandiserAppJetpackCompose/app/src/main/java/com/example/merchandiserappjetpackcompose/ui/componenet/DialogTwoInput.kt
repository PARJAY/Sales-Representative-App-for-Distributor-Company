package com.example.merchandiserappjetpackcompose.ui.componenet

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun DialogTwoInput(
    onDismissRequest: () -> Unit,
    onConfirmation: (String, Double) -> Unit,
    currentProductName : String = "",
    currentProductPrice : Double? = null
) {
    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableDoubleStateOf(0.0) }

    input1 = currentProductName.ifEmpty { "" }
    if (currentProductPrice != null) input2 = currentProductPrice

    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = {
            Text(text = "Input Data Produk Baru")
        },
        text = {
            Column {
                OutlinedTextField(
                    value = input1,
                    onValueChange = {input1 = it},
                    label = {
                        Text(text = "Enter Product Name")
                    },
                    isError = input1.isNotEmpty(),
                    supportingText = {
                        Text(text = "*required")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(8.dp))

                OutlinedTextField(
                    value = input2.toString(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {input2 = it.toDouble() },
                    label = {
                        Text(text = "Enter Product Price")
                    },
                    supportingText = {
                        Text(text = "*required")
                    },
                    modifier = Modifier.fillMaxWidth()

                )
            }
        },

        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation(input1, input2)
                }
            ) {
                Text("Confirm")
            }
        },

        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel")
            }
        },
    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DialogTwoInputProductPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            DialogTwoInput(
                onDismissRequest = {},
                onConfirmation = { _, _ -> }
            )
        }
    }
}