package com.iessanalberto.dam2.citasapp.data

import com.iessanalberto.dam2.citasapp.models.Cita
import java.util.Date

val listaCitas: MutableList<Cita> = mutableListOf(
    Cita(
        fecha = Date(), // Usa el momento actual.
        nombre = "Ricardo",
        descripcion = "sdasdas"
    )
)

