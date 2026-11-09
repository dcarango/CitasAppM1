package com.example.citasappm1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.citasappm1.ui.theme.CitasAppM1Theme
import com.iessanalberto.dam2.citasapp.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CitasAppM1Theme {
                AppNavigation()
            }
        }
    }
}
