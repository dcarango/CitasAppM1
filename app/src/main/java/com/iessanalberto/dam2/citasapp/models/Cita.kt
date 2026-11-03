package com.iessanalberto.dam2.citasapp.models

import java.util.Date

/**
 * Cita -
 * Clase que modela una cita con id, fecha, nombre y descripción.
 */

data class Cita(
    val id: Int = siguienteId(),
    var fecha: Date,
    var nombre: String,
    var descripcion: String
) {
    // Contador
    companion object {
        var contador = 0
        fun siguienteId():Int {
            return ++contador
        }
    }
}