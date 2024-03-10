package com.example.merchandiserappjetpackcompose

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun CopasableComposeFun() {
    Text("This is Explore Screen")
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun CopasableComposeFunPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            CopasableComposeFun()
        }
    }
}