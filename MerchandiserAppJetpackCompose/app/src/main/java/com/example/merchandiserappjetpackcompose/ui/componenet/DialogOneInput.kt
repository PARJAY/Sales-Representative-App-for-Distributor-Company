package com.example.merchandiserappjetpackcompose.ui.componenet

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.merchandiserappjetpackcompose.ui.componenet.logic.EnumDialogRequirment
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun DialogOneInput(
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit,
    enumDialogRequirment: EnumDialogRequirment,
    currentNameOrOutlet : String = ""
) {
    var text by remember { mutableStateOf("") }

    if (
        enumDialogRequirment == EnumDialogRequirment.EDIT_OUTLET ||
        enumDialogRequirment == EnumDialogRequirment.INPUT_NAME
        ) text = currentNameOrOutlet

    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = {
            Text(
                text =
                when (enumDialogRequirment) {
                    EnumDialogRequirment.INPUT_NAME -> "Input Nama Anda"
                    EnumDialogRequirment.INPUT_OUTLET -> "Input Data Outlet Baru"
                    EnumDialogRequirment.EDIT_OUTLET -> "Edit Data Outlet"
                }
            )
        },
        text = {
            Column {
                OutlinedTextField(
                    value = text,
                    onValueChange = {text = it},
                    label = {
                        Text(
                            text = when (enumDialogRequirment) {
                                EnumDialogRequirment.INPUT_NAME -> "Masukkan Nama"
                                EnumDialogRequirment.INPUT_OUTLET -> "Masukkan Nama Outlet Baru"
                                EnumDialogRequirment.EDIT_OUTLET -> "Edit Nama Outlet"
                            }
                        )
                    },
                    isError = text.isNotEmpty(),
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
                    onConfirmation(text)
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
fun DialogInputOutletPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            DialogOneInput(
                onDismissRequest = {},
                onConfirmation = {},
                EnumDialogRequirment.INPUT_OUTLET
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DialogEditOutletPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            DialogOneInput(
                onDismissRequest = {},
                onConfirmation = {},
                EnumDialogRequirment.EDIT_OUTLET
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DialogInputNamePreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            DialogOneInput(
                onDismissRequest = {},
                onConfirmation = {},
                EnumDialogRequirment.INPUT_NAME
            )
        }
    }
}