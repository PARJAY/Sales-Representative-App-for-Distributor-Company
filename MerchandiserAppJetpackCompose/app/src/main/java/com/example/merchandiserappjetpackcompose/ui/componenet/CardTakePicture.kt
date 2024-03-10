package com.example.merchandiserappjetpackcompose.ui.componenet

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merchandiserappjetpackcompose.R
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun CardTakePicture(
    supportingText : String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .width(150.dp)
            .border(BorderStroke(2.dp, Color.Black))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(ImageVector.vectorResource(R.drawable.ic_camera), contentDescription = null,
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
            )
            Text(
                fontSize = 13.sp,
                text = supportingText,
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CardTakePictureOdometerPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            CardTakePicture(
                supportingText = "Ambil Foto Odometer",
                modifier = Modifier.clickable {
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CardTakePictureOutletPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            CardTakePicture(
                supportingText = "Ambil Foto Outlet",
                modifier = Modifier.clickable {
                }
            )
        }
    }
}