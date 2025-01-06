package com.example.agendaapp_kotlin_jc.views.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agendaapp_kotlin_jc.R
import com.example.agendaapp_kotlin_jc.viewModels.RegisterViewModel

@Composable

fun RegisterView(navController: NavController, registerVM: RegisterViewModel){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ) {
        var email by remember{mutableStateOf("")} //Email
        var password by remember { mutableStateOf("")}//Password
        var username by remember { mutableStateOf("") }//User name
        //Titulo
        Text(
            modifier = androidx.compose.ui.Modifier.padding(top = 10.dp),
            text = "Registro de Usuario",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 20.sp)
        //Icono
        Image(
            modifier = androidx.compose.ui.Modifier
                .height(150.dp)
                .width(150.dp)
                .padding(15.dp),
            painter = painterResource(id = R.drawable.registro),
            contentDescription = "Logo de Registro"
        )
        //Username
        OutlinedTextField(
            value = username,
            onValueChange = {username = it},
            label = {Text(text = "Username")},
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        )
        //Email
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text(text = "Email")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        )
        //Password
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {Text(text = "Password")},
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(20.dp))

        //Boton de Registro

        Button(onClick = {
            registerVM.createUser(username,email,password){
                navController.navigate("Home")
            }
        },
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
            ) {
            Text(text = "Registrar")
        }
    }
}