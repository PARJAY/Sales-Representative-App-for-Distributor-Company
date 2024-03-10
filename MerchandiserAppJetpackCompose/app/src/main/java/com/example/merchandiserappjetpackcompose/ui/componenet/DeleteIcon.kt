package com.example.merchandiserappjetpackcompose.ui.componenet

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate

@Composable
fun DeleteIcon(
    cancleProductSelection : () -> Unit
) {
    Icon(
        imageVector = Icons.Default.AddCircle,
        contentDescription = "Cancle Product Selection",
        modifier = Modifier
            .rotate(45f)
            .clickable { 
                cancleProductSelection() 
            }
    )
}