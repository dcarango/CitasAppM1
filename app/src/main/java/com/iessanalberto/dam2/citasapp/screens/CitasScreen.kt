package com.iessanalberto.dam2.citasapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.iessanalberto.dam2.citasapp.navigation.AppScreens


/**
 * CitasScreen -
 * Ventana que hace uso de un lazyColum para mostrar todas las posibles citas.
 */
@Composable
fun CitasScreen(navController: NavController){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {navController.navigate(AppScreens.NewCitaScreen.route)}) {
                Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "Nueva cita")
            }
        }
    ) {
            paddingValues -> CitasScreenBodyContent(modifier = Modifier.padding(paddingValues = paddingValues),
        navController = navController)
    }
}

@Composable
fun CitasScreenBodyContent (modifier: Modifier, navController: NavController) {

}