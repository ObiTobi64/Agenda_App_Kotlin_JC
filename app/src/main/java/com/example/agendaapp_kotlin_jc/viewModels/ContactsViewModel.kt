package com.example.agendaapp_kotlin_jc.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactsViewModel :ViewModel(){
    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    fun saveContact(
        names:String,
        email:String,
        address:String,
        phone:String,
        onSuccess:()->Unit
    ){
        val myEmail=auth.currentUser?.email //Obteniendo nuestro email

        viewModelScope.launch (Dispatchers.IO){
            try {
                val contact =   hashMapOf(
                    "myEmail" to myEmail.toString().trim(),
                    "names" to names,
                    "email" to email,
                    "address" to address,
                    "phone" to phone
                )

                firestore.collection("Contacts").add(contact)

                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {e->
                        Log.d("Error al registrar el contacto","No se registro")
                    }
            }catch (e:Exception){
                Log.d("Error al registrar contacto","No se registro")
            }
        }
    }
}