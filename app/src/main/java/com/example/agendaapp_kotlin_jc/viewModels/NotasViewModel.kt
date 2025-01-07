package com.example.agendaapp_kotlin_jc.viewModels

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class NotasViewModel :ViewModel() {
    private val auth : FirebaseAuth = Firebase.auth

    fun logOut (){
        auth.signOut() //Cerrar Sesion
    }
}