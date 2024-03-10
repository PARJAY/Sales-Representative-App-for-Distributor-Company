package com.example.merchandiserappjetpackcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.merchandiserappjetpackcompose.ui.navigation.NavigationSceren

@Composable
fun SalesRepresentativeApp(
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    NavigationSceren(
        navController,
        modifier = Modifier.padding()
    )
}