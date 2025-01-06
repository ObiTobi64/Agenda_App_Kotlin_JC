package com.example.agendaapp_kotlin_jc.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val auth : FirebaseAuth = Firebase.auth

    fun login (email:String,password:String, onSuccess: () -> Unit){
        viewModelScope.launch {
            try {
                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener {task->
                        if (task.isSuccessful){
                            onSuccess()
                        }

                    }

                    .addOnFailureListener {
                        Log.d("Error", "No se pudo iniciar sesión")
                    }
            }catch (e:Exception){
                Log.d("Error", "${e.message}")
            }
        }
    }
}