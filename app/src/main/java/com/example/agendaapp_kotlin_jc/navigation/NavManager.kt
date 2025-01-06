package com.example.agendaapp_kotlin_jc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agendaapp_kotlin_jc.viewModels.LoginViewModel
import com.example.agendaapp_kotlin_jc.viewModels.NotasViewModel
import com.example.agendaapp_kotlin_jc.viewModels.RegisterViewModel
import com.example.agendaapp_kotlin_jc.views.login.LoginView
import com.example.agendaapp_kotlin_jc.views.notas.HomeView
import com.example.agendaapp_kotlin_jc.views.register.RegisterView

@Composable
fun NavManager(
    loginVM: LoginViewModel,
    registerVM : RegisterViewModel,
    notesVM : NotasViewModel
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Login"){
        composable("Login"){
            LoginView(navController,loginVM)

        }
        composable("Register"){
            RegisterView(navController, registerVM)
        }
        composable("Home"){
            HomeView(navController, notesVM)
        }
    }
}