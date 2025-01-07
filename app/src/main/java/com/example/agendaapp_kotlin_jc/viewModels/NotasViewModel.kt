package com.example.agendaapp_kotlin_jc.viewModels

import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotasViewModel :ViewModel() {
    private val auth : FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore //Base de datos

    fun saveNotes(title:String, description:String , onSuccess: () ->Unit){
        val email = auth.currentUser?.email
        viewModelScope.launch (Dispatchers.IO){
            try {
                val note = hashMapOf(
                    "title" to title,
                    "description" to description,
                    "fecha" to formatDate(),
                    "email" to email.toString().trim()
                )

                firestore.collection("Notes").add(note)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {e->
                        Log.d("Error","${e.message}")
                    }
            }catch (e:Exception){
                Log.d("Error","No se pudo registrar la nota")
            }
        }

    }

    private fun formatDate() :String{
        val currentDate: Date = Calendar.getInstance().time
        val formatDate =SimpleDateFormat("dd/MM/yyyy hh:mm:a" , Locale.getDefault())
        return formatDate.format(currentDate)
    }

    fun logOut (){
        auth.signOut() //Cerrar Sesion
    }
}