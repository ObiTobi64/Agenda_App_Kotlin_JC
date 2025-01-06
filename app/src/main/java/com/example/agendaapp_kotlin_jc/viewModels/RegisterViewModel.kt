package com.example.agendaapp_kotlin_jc.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agendaapp_kotlin_jc.models.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val auth : FirebaseAuth = Firebase.auth

    fun createUser(username : String, email : String, password:String, onSuccess : () -> Unit){
        viewModelScope.launch {
            try {
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener{task->
                        if(task.isSuccessful){
                            saveUser(username)
                            onSuccess()
                        }else{
                            Log.d(
                                "Error", "Error al crear cuenta"
                                )
                        }

                    }
            }catch(e:Exception){
                Log.d(
                    "Error", "${e.message}"
                )
            }
        }
    }
    fun saveUser(username: String){
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        viewModelScope.launch (Dispatchers.IO){
            val user = UserModel(
                userId = id.toString(),
                email = email.toString(),
                username = username
            )

            FirebaseFirestore.getInstance().collection("Users")
                .add(user)
                .addOnSuccessListener {
                    Log.d(
                        "Success", "Se guardo la informacion del usuario"
                    )
                }

                .addOnFailureListener {e->
                    Log.d(
                        "Error", "${e.message}"
                    )
                }
        }
    }
}