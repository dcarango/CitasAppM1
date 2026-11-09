package com.example.citasappm1.components

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Componente modular de DatePicker para reutilizar en cualquier pantalla.
 * Recibe directamente un Date? y devuelve el cambio mediante onDateSelected.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerField(
    label: String,
    selectedDate: Date?,
    onDateSelected: (Date?) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "DD/MM/YYYY"
) {
    var showModal by remember { mutableStateOf(false) }
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val dateText = selectedDate?.let { formatter.format(it) } ?: ""

    // Campo de texto visual
    OutlinedTextField(
        value = dateText,
        onValueChange = { },
        readOnly = true,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        trailingIcon = {
            if (selectedDate != null) {
                IconButton(onClick = { onDateSelected(null) }) {
                    Icon(Icons.Default.Clear, contentDescription = "Limpiar fecha")
                }
            } else {
                Icon(Icons.Default.DateRange, contentDescription = "Seleccionar fecha")
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .pointerInput(selectedDate) {
                awaitEachGesture {
                    awaitFirstDown(pass = PointerEventPass.Initial)
                    val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                    if (upEvent != null) {
                        showModal = true
                    }
                }
            }
    )

    // Diálogo modal que se abre al tocar el campo
    if (showModal) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDate?.time
        )

        DatePickerDialog(
            onDismissRequest = { showModal = false },
            confirmButton = {
                TextButton(onClick = {
                    val millis = datePickerState.selectedDateMillis
                    onDateSelected(millis?.let { Date(it) })
                    showModal = false
                }) {
                    Text("Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showModal = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}