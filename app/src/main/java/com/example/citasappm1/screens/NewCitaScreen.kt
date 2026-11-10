package com.iessanalberto.dam2.citasapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.citasappm1.views.CitaViewModel

/**
 * NewCitasScreen -
 *
 */
@Composable
fun NewCitasScreen (navController: NavController) {


    val citaViewModel: CitaViewModel = viewModel()
    val citaState by citaViewModel.citaState.collectAsState()

}