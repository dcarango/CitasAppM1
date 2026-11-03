package com.iessanalberto.dam2.citasapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.iessanalberto.dam2.citasapp.screens.CitasScreen
import com.iessanalberto.dam2.citasapp.screens.EditDeleteCitaScreen
import com.iessanalberto.dam2.citasapp.screens.NewCitasScreen

@Composable
fun AppNavigation () {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.CitasScreen.route) {
        composable (route = AppScreens.CitasScreen.route) { CitasScreen(navController = navController) }
        composable (route = AppScreens.NewCitaScreen.route) { NewCitasScreen(navController = navController) }
        composable (route = AppScreens.EditDeleteCitaScreen.route + "/{id}",
            arguments = listOf(navArgument(name = "id") {type = NavType.IntType})
            ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: -1
            EditDeleteCitaScreen(navController = navController, id = id)
        }
    }
}