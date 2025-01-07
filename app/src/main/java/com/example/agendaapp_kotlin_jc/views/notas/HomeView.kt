package com.example.agendaapp_kotlin_jc.views.notas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agendaapp_kotlin_jc.viewModels.NotasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun HomeView(navController: NavController, notesVM: NotasViewModel){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text( text = "Inicio") },
                actions = {
                    IconButton(onClick = {
                        notesVM.logOut()
                        navController.popBackStack()
                    }){
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription="")
                    }
                }
            )
        }
    ) {padding->
        Column (modifier = Modifier.padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally){

            Button(onClick = {
                navController.navigate("AddNote")
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)) {
                Text(text = "Agregar Nota")
            }
        }
    }


}