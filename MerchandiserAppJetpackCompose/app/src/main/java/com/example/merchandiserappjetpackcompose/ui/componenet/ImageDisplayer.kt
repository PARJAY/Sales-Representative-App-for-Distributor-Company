package com.example.merchandiserappjetpackcompose.ui.componenet

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun ImageDisplayer(
    supportingDescription: String = "",
    imageUri: Uri,
    onClick: () -> Unit
) {
    Image(
        contentDescription = supportingDescription,
        contentScale = ContentScale.Crop,
        painter = rememberAsyncImagePainter(imageUri),
        modifier = Modifier
            .height(100.dp)
            .width(150.dp)
            .border(BorderStroke(2.dp, Color.Red))
            .clickable {
                onClick()
            }
    )
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ImageDisplayerPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            val capturedImageUri by remember {
                mutableStateOf<Uri>(Uri.EMPTY)
            }

            ImageDisplayer(
                "",
                capturedImageUri,
                onClick = {}
            )
        }
    }
}
