package com.aboj.lab5mvvm.Views

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aboj.lab5mvvm.ViewModel.GeneralViewModel
import com.aboj.lab5mvvm.Model.Card
import com.aboj.lab5mvvm.Model.Task
import java.util.*

@Composable
fun TodoScreen(viewModel: GeneralViewModel) {
    val tasks = viewModel.tasks.collectAsState()
    val lista = remember { mutableStateListOf<Card>() }
    var showDialog by remember { mutableStateOf(false) }
    var newTitle by remember { mutableStateOf("") }
    var newDescription by remember { mutableStateOf("") }

    lista.clear()
    tasks.value.forEach { task ->
        Log.d("Task", task.toString())
        lista.add(
            Card(
                pos = task.id,
                title = task.title,
                description = task.description,
                endDate = task.endDate,
                checked = task.isCompleted
            )
        )
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = {
                    if (newTitle.isNotBlank()) {
                        val task = Task(
                            id = Random().nextInt(),
                            title = newTitle,
                            description = newDescription,
                            endDate = Date(),
                            isCompleted = false
                        )
                        viewModel.addTask(task)
                        newTitle = ""
                        newDescription = ""
                        showDialog = false
                    }
                }) {
                    Text("Guardar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            },
            title = { Text("Agrega una tarea!") },
            text = {
                Column {
                    OutlinedTextField(
                        value = newTitle,
                        onValueChange = { newTitle = it },
                        label = { Text("Título de la tarea") },
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = newDescription,
                        onValueChange = { newDescription = it },
                        label = { Text("Descripcion") },
                        singleLine = false
                    )
                }
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(lista) { card ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primary)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Título: ${card.title}")
                        Text("Descripción: ${card.description}")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar TArea")
        }
    }
}

