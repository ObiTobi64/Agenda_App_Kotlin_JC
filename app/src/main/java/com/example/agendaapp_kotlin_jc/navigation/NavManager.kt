package com.example.agendaapp_kotlin_jc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.agendaapp_kotlin_jc.viewModels.ContactsViewModel
import com.example.agendaapp_kotlin_jc.viewModels.LoginViewModel
import com.example.agendaapp_kotlin_jc.viewModels.NotasViewModel
import com.example.agendaapp_kotlin_jc.viewModels.RegisterViewModel
import com.example.agendaapp_kotlin_jc.views.contacts.AddContactsView
import com.example.agendaapp_kotlin_jc.views.contacts.AllContactsView
import com.example.agendaapp_kotlin_jc.views.contacts.EditContactView
import com.example.agendaapp_kotlin_jc.views.login.CheckSesionView
import com.example.agendaapp_kotlin_jc.views.login.LoginView
import com.example.agendaapp_kotlin_jc.views.notas.AddNoteView
import com.example.agendaapp_kotlin_jc.views.notas.AllNotesView
import com.example.agendaapp_kotlin_jc.views.notas.EditNoteView
import com.example.agendaapp_kotlin_jc.views.notas.HomeView
import com.example.agendaapp_kotlin_jc.views.register.RegisterView

@Composable
fun NavManager(
    loginVM: LoginViewModel,
    registerVM : RegisterViewModel,
    notesVM : NotasViewModel,
    contactVM :ContactsViewModel
){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "CheckSesion"){
        composable("CheckSesion"){
            CheckSesionView(navController)
        }

        composable("Login"){
            LoginView(navController,loginVM)

        }
        composable("Register"){
            RegisterView(navController, registerVM)
        }
        composable("Home"){
            HomeView(navController, notesVM)
        }

        composable("AddNote"){
            AddNoteView(navController, notesVM)
        }

        composable("AllNotes"){
            AllNotesView(navController,notesVM)
        }

        composable("EditNote/{idNote}", arguments = listOf(
            navArgument("idNote"){type= NavType.StringType}
        )){
            val idNote = it.arguments?.getString("idNote")?:""
            EditNoteView(navController,notesVM,idNote)
        }

        composable("AddContact"){
            AddContactsView(navController,contactVM)
        }

        composable("AllContacts"){
            AllContactsView(navController,contactVM)
        }

        composable("EditContact/{idContact}", arguments = listOf(
            navArgument("idContact"){type = NavType.StringType}
        )){
            val idContact = it.arguments?.getString("idContact")?:""
            EditContactView(navController,contactVM,idContact)
        }
    }
}