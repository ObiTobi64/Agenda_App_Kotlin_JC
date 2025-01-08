package com.example.agendaapp_kotlin_jc.views.notas

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agendaapp_kotlin_jc.viewModels.NotasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun EditNoteView(
    navController: NavController,
    notesVM:NotasViewModel,
    idNote:String
){

    LaunchedEffect(Unit) {
        notesVM.getNoteId(idNote)
    }

    val state = notesVM.state
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Agregar nota") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack() //Regresar a la vista anterior
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                })
        }

    ){paddingValues ->
        Column (modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
            //Ingresar un titulo
            OutlinedTextField(
                value = state.title,
                onValueChange = {notesVM.onValue(it,"title")},
                label = {Text(text ="Titulo")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )
            //Ingresar una descripcion
            OutlinedTextField(
                value = state.description,
                onValueChange = {notesVM.onValue(it,"description")},
                label = {Text(text = "Descripcion")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp , end = 20.dp)
            )
            //Actualizar nota
            Button(
                onClick = {
                    notesVM.editNote(idNote){
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp , end = 20.dp)
            ) {
                Text(text = "Actualizar nota")
            }

            Button(onClick = {
                notesVM.deleteNote(idNote){
                    navController.popBackStack()
                }
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, end = 20.dp)) {
                Text(text = "Eliminar nota")
            }

        }
    }
}