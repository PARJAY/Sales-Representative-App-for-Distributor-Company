package com.example.merchandiserappjetpackcompose.ui.componenet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DynamicTextFieldLayout() {
    val inputItems = remember { mutableStateListOf<Int>() }
    val labelItems = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(onClick = {
            inputItems.add(0)
            labelItems.add("Product Name")
        }) {
            Text(text = "Add Input")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            for (index in inputItems.indices) {
                val item = inputItems[index]
                var label = labelItems[index]

                OutlinedTextField(
                    value = item.toString(),
                    onValueChange = {
                        inputItems[index] = it.toInt()
                    },
                    label = { Text(label) },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                // Open multiple choice dialog
//                                val dialogResult = showMultipleChoiceDialog(/* ... */)
//                                if (dialogResult != null) {
//                                    label = dialogResult // Update label with selected value
//                                    labelItems[index] = label // Update label list
//                                    inputItems[index] = 0 // Reset item value
//                                }
                            }
                        ) {
                            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Add New Report")
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}