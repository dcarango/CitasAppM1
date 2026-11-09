package com.example.citasappm1.states
import com.iessanalberto.dam2.citasapp.models.Cita
import java.util.Date

/**
 * CitaState -
 * Se inicializa, importante los null para que no bajen.
 * En la logica el state solo se encarga de los valores por defecto. El ViewModel es quien
 * se encarga de poblar de lista con los datos del data.
 */

data class CitaState(
    val listaCitas: List<Cita> = emptyList(),
    val fechaFiltro: Date? = null,
    val nombre: String = "",
    val descripcion: String = "",
    val fechaFormulario: Date? = null
)