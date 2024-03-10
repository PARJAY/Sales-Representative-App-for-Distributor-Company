package com.example.merchandiserappjetpackcompose.ui.componenet

import android.content.res.Configuration
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun DialogConfirmation(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    selectedItemName: String,
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = {
            Text(text = "Peringatan")
        },
        text = {
            Text(
                buildAnnotatedString {
                    append("Apakah anda yakin ingin menghapus ")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(selectedItemName)
                    }
                }
            )
        },
        confirmButton = {
            TextButton({
                onConfirmation()}
            ) {
                Text("Ya")
            }
        },
        dismissButton = {
            TextButton({
                onDismissRequest()
            }) {
                Text("Tidak")
            }
        }
    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DialogConfirmationPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            DialogConfirmation(
                onDismissRequest = {},
                onConfirmation = {},
                "Outlet Singaraja",
            )
        }
    }
}

