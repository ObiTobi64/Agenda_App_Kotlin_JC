package com.example.agendaapp_kotlin_jc.views.notas

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.agendaapp_kotlin_jc.viewModels.NotasViewModel

@Composable

fun HomeView(navController: NavController, notesVM: NotasViewModel){
    Text(text = "Inicio")
}