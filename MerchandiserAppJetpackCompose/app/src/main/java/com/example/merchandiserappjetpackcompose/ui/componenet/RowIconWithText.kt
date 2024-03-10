package com.example.merchandiserappjetpackcompose.ui.componenet

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun RowIconWithText(
    textInformation: String,
    onClickAction: () -> Unit,
    imageVector: ImageVector = Icons.Default.AddCircle
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clickable { onClickAction() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Add",
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = textInformation,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RowIconWithTextForAddOutletItemPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            RowIconWithText(
                "Tambah Outlet",
                onClickAction = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RowIconWithTextForEditProductItemPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            RowIconWithText(
                "Edit Produk Daftar Jualan",
                onClickAction = {},
                Icons.Default.Edit
            )
        }
    }
}