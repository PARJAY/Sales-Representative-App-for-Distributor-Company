package com.example.merchandiserappjetpackcompose.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.merchandiserappjetpackcompose.ui.componenet.DialogOneInput
import com.example.merchandiserappjetpackcompose.ui.componenet.ModifiedButton
import com.example.merchandiserappjetpackcompose.ui.componenet.logic.EnumDialogRequirment
import com.example.merchandiserappjetpackcompose.ui.navigation.Screen
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun MainMenuScreen(
    onNavigateTo: (String) -> Unit,
    changeSalesRepresentativeName : (String) -> Unit,
    salesRepresentativeName : String,
) {
    val contex = LocalContext.current
    val openDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()), // Add scrolling if needed
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Aplikasi Sales Representatif",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            lineHeight = 48.sp,
            modifier = Modifier.padding(top = 32.dp)
        )

        Text(
            text = salesRepresentativeName.ifEmpty { "Nama Anda Belum Terdaftar" },

            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )

        Row (
            modifier = Modifier.padding(top = 200.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                ModifiedButton(
                    onClick = {openDialog.value = true},
                    text = "Input Nama Anda"
                )

                Spacer(modifier = Modifier.height(16.dp))

                ModifiedButton(
                    onClick = {
                        if (salesRepresentativeName != "") onNavigateTo(Screen.OutletList.route)
                        else {
                            openDialog.value = true
                            Toast.makeText(contex, "Masukkan Nama Terlebih Dahulu", Toast.LENGTH_SHORT).show()
                        }
                    },
                    text = "Data Outlet"
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                ModifiedButton(
                    onClick = {
                        if (salesRepresentativeName != "") onNavigateTo(Screen.TodayReport.route)
                        else {
                            openDialog.value = true
                            Toast.makeText(contex, "Masukkan Nama Terlebih Dahulu", Toast.LENGTH_SHORT).show()
                        }
                    },
                    text = "Laporan Hari Ini"
                )

                Spacer(modifier = Modifier.height(16.dp))

                ModifiedButton(
                    onClick = {
                        if (salesRepresentativeName != "") onNavigateTo(Screen.ProductList.route)
                        else {
                            openDialog.value = true
                            Toast.makeText(contex, "Masukkan Nama Terlebih Dahulu", Toast.LENGTH_SHORT).show()
                        }
                    },
                    text = "Product Data"
                )
            }
        }
    }

    if (openDialog.value) {
        DialogOneInput(
            onDismissRequest = { openDialog.value = false },
            onConfirmation = {
                changeSalesRepresentativeName(it)
                openDialog.value = false
            },
            EnumDialogRequirment.INPUT_NAME,
            salesRepresentativeName
        )
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainMenuScreenNameNotInsertedPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            MainMenuScreen(
                onNavigateTo = {},
                changeSalesRepresentativeName = {},
                ""
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainMenuScreenNameInsertedPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            MainMenuScreen(
                onNavigateTo = {},
                changeSalesRepresentativeName = {},
                "Sastra Gunawan"
            )
        }
    }
}