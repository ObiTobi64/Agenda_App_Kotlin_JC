package com.example.agendaapp_kotlin_jc.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agendaapp_kotlin_jc.ui.theme.White

@Composable

fun CardNote(
    title:String,
    description:String,
    fecha:String,
    onClick:() -> Unit
){
    Card (
        modifier = Modifier
            .padding(20.dp),
        shape = RoundedCornerShape(
             topEnd = 10.dp,
            topStart = 10.dp,
            bottomEnd = 10.dp,
            bottomStart = 10.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2987AD))
    ){
        Column (
            modifier = Modifier
                .padding(10.dp)
        ){
            //Titulo
        Text(text = title, color = White, modifier = Modifier.fillMaxWidth(), fontSize = 20.sp)
            //Description
        Text(text = description, color = White)
        }

        Row (modifier = Modifier.padding(10.dp)){
            //Fecha de Registro de la nota
            Text(text = fecha, color = White)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick={onClick()}) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "", tint = Color.White)
            }
        }
    }
}