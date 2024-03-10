package com.example.merchandiserappjetpackcompose.ui.componenet

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.merchandiserappjetpackcompose.model.Outlet
import com.example.merchandiserappjetpackcompose.tools.DummyGlobalVariable
import com.example.merchandiserappjetpackcompose.ui.componenet.logic.EnumDialogRequirment
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun OutletItem(
    outlet: Outlet,
    onUpdateAction : (Outlet) -> Unit,
    onDeleteAction : (Outlet) -> Unit
) {
    val openEditOutletDialog = remember { mutableStateOf(false) }
    val openOutletDeleteConfirmationDialog = remember { mutableStateOf(false) }

    if (openEditOutletDialog.value) DialogOneInput(
        onDismissRequest = { openEditOutletDialog.value = false },
        onConfirmation = {
            outlet.outletName = it
            onUpdateAction(outlet)
            openEditOutletDialog.value = false
        },
        EnumDialogRequirment.EDIT_OUTLET,
        outlet.outletName
    )

    if (openOutletDeleteConfirmationDialog.value) DialogConfirmation(
        onDismissRequest = { openOutletDeleteConfirmationDialog.value = false },
        onConfirmation = {
            onDeleteAction(outlet)
            openOutletDeleteConfirmationDialog.value = false
        },
        "Outlet ${outlet.outletName}"
    )

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = outlet.outletName,
                style = MaterialTheme.typography.titleLarge
            )
            Row {

                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        openEditOutletDialog.value = true
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        openOutletDeleteConfirmationDialog.value = true
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OutletItemPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            OutletItem(
                DummyGlobalVariable.LIST_OUTLET_DUMMY[0],
                {},
                {}
            )
        }
    }
}