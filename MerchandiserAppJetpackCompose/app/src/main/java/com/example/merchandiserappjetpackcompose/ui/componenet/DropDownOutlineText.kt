package com.example.merchandiserappjetpackcompose.ui.componenet

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import com.example.merchandiserappjetpackcompose.model.Outlet
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun DropDownOutlineText(
    selectedOutlet : String,
    menuItems : List<Outlet>,
    onItemSelected: (Outlet) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded) Icons.Filled.KeyboardArrowUp
    else Icons.Filled.KeyboardArrowDown

    Column (modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedOutlet,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textfieldSize = coordinates.size.toSize()   // This value is used to assign to the DropDown the same width
                },
            label = { Text("Nama Outlet") },
            trailingIcon = {
                Icon(
                    icon,"contentDescription",
                    Modifier.clickable { expanded = !expanded }
                )
            },
            readOnly = true,

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current){textfieldSize.width.toDp()})
        ) {
            if (menuItems.isEmpty())
                DropdownMenuItem(
                    onClick = {},
                    text = { Text(text = "Data Outlet Kosong") }
                )

            menuItems.forEach { outlet ->
                DropdownMenuItem(
                    onClick = {
                        onItemSelected(outlet)
                        expanded = false
                    },
                    text = { Text(text = outlet.outletName) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DropDownOutlineTextPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            DropDownOutlineText(
                "Singaraja",
                emptyList(),
                onItemSelected = {}
            )
        }
    }
}
