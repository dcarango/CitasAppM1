package com.iessanalberto.dam2.citasapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.citasappm1.components.MyDatePicker
import com.example.citasappm1.views.CitaViewModel

/**
 * NewCitasScreen -
 * En esta ventana se agregan citas a una listaCitas que está alojada en el paquete data
 * no se accede directamente, el paso es: ViewModel - fun guardarCitas - listaCitas(data).
 */
@Composable
fun NewCitasScreen (navController: NavController) {
    Scaffold() {
            innerpadding -> NewCitasScreenBodyContent(
        navController = navController, modifier = Modifier.padding(innerpadding))
    }
}

@Composable
fun NewCitasScreenBodyContent (navController: NavController, modifier: Modifier) {

    val citaViewModel: CitaViewModel = viewModel()
    val citaState by citaViewModel.citaState.collectAsState()
    val context= LocalContext.current

    Column(modifier = modifier
        .fillMaxSize().padding(16.dp), // Margen del borde de la pantalla
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        // OutlineMyDatePiciker
        MyDatePicker(
            label = "Ingrese una fecha", // Label de dentro
            selectedDate = citaState.fechaFormulario,
            onDateSelected = { fechaSeleccionada ->
                citaViewModel.onFechaFormularioChanged(fechaSeleccionada)
            },
        )

        // Campos nombre y descripción
        OutlinedTextField(
            value = citaState.nombre,
            onValueChange = {
                texto -> citaViewModel.onNombreChanged(texto)
            },
            label = { Text(text = "Nombre") },
            modifier = Modifier.fillMaxWidth() // Ancho completo
        )
        OutlinedTextField(
            value = citaState.descripcion,
            onValueChange = {
                texto -> citaViewModel.onDescripcionChanged(texto)
            },
            label = { Text(text = "Descripcion") },
            modifier = Modifier.fillMaxWidth()
        )

        // Boton guardar
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            if (citaState.nombre.isBlank() || citaState.descripcion.isBlank()){
                Toast.makeText(context,"Los campos debes estar llenos",Toast.LENGTH_LONG).show()
            } else {
                citaViewModel.agregarCita()
                Toast.makeText(context,"Tarea añadida con exito",Toast.LENGTH_LONG).show()
                navController.popBackStack() // Pa volver atrás
            }
        }) {
            Text(text = "Guardar")
        }
    }
}