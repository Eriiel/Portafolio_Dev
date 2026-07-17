package com.example.cuatropantallas.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cuatropantallas.screens.DetailsScreen
import com.example.cuatropantallas.screens.HomeScreen
import com.example.cuatropantallas.screens.ProfileScreen
import com.example.cuatropantallas.screens.SettingsScreen

data class BottomNavItem(
    val label: String,
    val route: String
)

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val items = listOf(
        BottomNavItem("Inicio", "home"),
        BottomNavItem("Perfil", "profile"),
        BottomNavItem("Ajustes", "settings"),
        BottomNavItem("Detalles", "details")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(

        bottomBar = {

            NavigationBar {

                items.forEach { item ->

                    NavigationBarItem(
                        selected = currentRoute == item.route,

                        onClick = {
                            navController.navigate(item.route)
                        },

                        icon = {

                            when (item.route) {

                                "home" -> Icon(
                                    Icons.Default.Home,
                                    contentDescription = item.label
                                )

                                "profile" -> Icon(
                                    Icons.Default.Person,
                                    contentDescription = item.label
                                )

                                "settings" -> Icon(
                                    Icons.Default.Settings,
                                    contentDescription = item.label
                                )

                                "details" -> Icon(
                                    Icons.Default.Info,
                                    contentDescription = item.label
                                )
                            }
                        },

                        label = {
                            Text(item.label)
                        }
                    )
                }
            }
        }

    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("home") {
                HomeScreen(navController)
            }

            composable("profile") {
                ProfileScreen(navController)
            }

            composable("settings") {
                SettingsScreen(navController)
            }

            composable("details") {
                DetailsScreen(navController, "123")
            }
        }
    }
}