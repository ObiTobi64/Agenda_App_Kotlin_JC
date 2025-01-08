package com.example.agendaapp_kotlin_jc.views.notas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.agendaapp_kotlin_jc.components.CardNote
import com.example.agendaapp_kotlin_jc.viewModels.NotasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun AllNotesView(navController: NavController,notasVM:NotasViewModel){

    LaunchedEffect(Unit) {
        notasVM.getNotes()
    }
    Scaffold (topBar = {
        TopAppBar(
            title = {Text(text="Mis notas")},
            navigationIcon = {
                IconButton(onClick = {navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                         contentDescription = "")
                }
            }
        )
    }){padding->
        Column(
            modifier = Modifier.padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val note by notasVM.noteData.collectAsState()

            LazyColumn {
                items(note){item->
                    CardNote(title = item.title,
                        description = item.description,
                        fecha = item.fecha) { }
                }
            }
        }
    }
}