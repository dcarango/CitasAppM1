package com.example.citasappm1.views

import androidx.lifecycle.ViewModel
import com.example.citasappm1.states.CitaState
import com.iessanalberto.dam2.citasapp.data.listaTareas
import com.iessanalberto.dam2.citasapp.models.Cita
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class CitaViewModel : ViewModel() {

    private val _citaState = MutableStateFlow(CitaState())
    val citaState: StateFlow<CitaState> = _citaState.asStateFlow()

    // Funciones onChange
    fun onNombreChanged(nombre: String) {
        _citaState.update { currentState ->
            currentState.copy(nombre = nombre)
        }
    }
    fun onDescripcionChanged(descripcion: String) {
        _citaState.update { currentState ->
            currentState.copy(descripcion = descripcion)
        }
    }
    fun onFechaFiltroChanged(fecha: Date?) {
        _citaState.update { currentState ->
            currentState.copy(fechaFiltro = fecha)
        }
    }

    // Funciones CRUD
    fun agregarCita() {
        val state = _citaState.value // Consulta los valores que hay guardados en ese instante para operar con ellos.
        val fecha = state.fechaFormulario ?: Date() // Si no elige fecha, usa la actual

        val nuevaCita = Cita( // Esto es como un Cita cita = new Cita;
            // el ide por defecto lo tenemos incremental de la funcion de clase
            fecha = fecha, // fecha de arriba
            nombre = state.nombre, // el famoso state
            descripcion = state.descripcion // más de lo mismo
        )

        // Se añade en la data
        listaTareas.add(nuevaCita)

        // Limpiamos los campos
        _citaState.update { currentState ->
            currentState.copy(
                listaCitas = listaTareas.toList(), // toLis: cambio de objeto, muestra la nueva cita en el LazyColumn
                nombre = "",
                descripcion = "",
                fechaFormulario = null
            )
        }
    }
}