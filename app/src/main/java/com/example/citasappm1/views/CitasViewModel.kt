package com.example.citasappm1.views

import androidx.lifecycle.ViewModel
import com.example.citasappm1.states.CitaState
import com.iessanalberto.dam2.citasapp.data.listaCitas
import com.iessanalberto.dam2.citasapp.models.Cita
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

class CitaViewModel : ViewModel() {

    // Pasamos listaCitas del data, ya no es empty
    private val _citaState = MutableStateFlow(CitaState(listaCitas = listaCitas))
    val citaState: StateFlow<CitaState> = _citaState.asStateFlow()

    // Funciones estado
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
    fun onFechaFormularioChanged(fecha: Date?) {
        _citaState.update { currentState ->
            currentState.copy(fechaFormulario = fecha)
        }
    }

    // Funciones negocio
    fun agregarCita() {
        /** .citaValue:
         * Forma de poder consultar los datos del state. Esto incluye funciones onChange, ya que el
         * en el onChange se realiza un update del _state.
         */
        val state = _citaState.value
        val fecha = state.fechaFormulario ?: Date() // Si no elige fecha, usa la actual

        val nuevaCita = Cita(
            // el id por defecto lo tenemos incremental de la funcion de clase
            fecha = fecha, // fecha de arriba
            nombre = state.nombre, // el famoso state
            descripcion = state.descripcion // más de lo mismo
        )
        // Se añade en la data
        listaCitas.add(nuevaCita)
    }

    fun editarCita(id: Int) {
        val citaAEditar = buscarCita(id)



    }

    fun eliminarCita(cita: Cita) {
        listaCitas.remove(cita)
    }

    fun buscarCita(id: Int): Cita? {
        val state = _citaState
        var citaEncontrada: Cita? = null

        for (cita in state.value.listaCitas) {
            if (cita.id == id)
                citaEncontrada = cita
        }
        return citaEncontrada
    }
}