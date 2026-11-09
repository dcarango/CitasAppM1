package com.iessanalberto.dam2.citasapp.navigation

sealed class AppScreens (val route: String) {
    object CitasScreen: AppScreens(route = "citas_screen")
    object NewCitaScreen: AppScreens(route = "new_cita_screen")
    object EditDeleteCitaScreen: AppScreens(route = "edit_delete_cita_screen")
}