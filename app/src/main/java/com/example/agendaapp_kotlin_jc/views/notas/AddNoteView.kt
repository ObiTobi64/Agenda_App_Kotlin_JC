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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agendaapp_kotlin_jc.viewModels.NotasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun AddNoteView(navController: NavController,notesVM: NotasViewModel){
    var title by remember { mutableStateOf("") }
    var descriptions by remember { mutableStateOf("") }
    val context = LocalContext.current

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
                })}

    ){paddingValues ->
        Column (modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally){
            //Ingresar un titulo
            OutlinedTextField(
                value = title,
                onValueChange = {title=it},
                label = {Text(text ="Titulo")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )
            //Ingresar una descripcion
            OutlinedTextField(
                value = descriptions,
                onValueChange = {descriptions = it},
                label = {Text(text = "Descripcion")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp , end = 20.dp)
            )
            //Agregar nota a la base de datos
            Button(
                onClick = {
                    notesVM.saveNotes(title,descriptions){
                        Toast.makeText(context,"Nota guardada con exito",Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp , end = 20.dp)
            ) {
                Text(text = "Agregar nota")
            }
        }
    }
}