package com.iessanalberto.dam2.citasapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.citasappm1.components.CitaCard
import com.example.citasappm1.components.MyDatePicker
import com.example.citasappm1.views.CitaViewModel
import com.iessanalberto.dam2.citasapp.navigation.AppScreens
import java.text.SimpleDateFormat
import java.util.Locale


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

    val citaViewModel: CitaViewModel = viewModel()
    val citaState by citaViewModel.citaState.collectAsState()

    var mostrarDatePicker by remember { mutableStateOf(false) } // Bandera para el dater
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) // Formatear fecha

    // Filtro inicial
    val citasAMostrar = if (citaState.fechaFiltro == null) {
        citaState.listaCitas
        // Si está vacia la fecha seleccionada (fechafiltro), entonces que muestre
        // la lista entera
    } else {
        // de lo contrario, mostrará la lista con las fechas seleccionadas (fechafiltro)
        citaState.listaCitas.filter { cita ->
            dateFormat.format(cita.fecha) == dateFormat.format(citaState.fechaFiltro!!)
        }
    }

    // Colum base
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // DatterPicker
        MyDatePicker(
            label = "Filtrar por fecha", // Label de dentro
            selectedDate = citaState.fechaFiltro,
            onDateSelected = { fechaSeleccionada ->
                citaViewModel.onFechaFiltroChanged(fechaSeleccionada)
            },
            modifier = Modifier.padding(16.dp)
        )

        if (citasAMostrar.isEmpty()) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Sin citas registradas")
        } else {
            // LazyColum
            LazyColumn(
                modifier = modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    citasAMostrar
                ) {
                        cita ->
                    CitaCard(cita = cita, onClick = {navController.navigate(route = AppScreens.EditDeleteCitaScreen.route + "/${cita.id}")})
                }
            }
        }
    }
}