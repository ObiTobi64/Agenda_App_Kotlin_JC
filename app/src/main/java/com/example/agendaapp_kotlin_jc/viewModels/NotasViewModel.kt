package com.example.agendaapp_kotlin_jc.viewModels

import android.icu.util.Calendar
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agendaapp_kotlin_jc.models.NoteModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotasViewModel :ViewModel() {
    private val auth : FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore //Base de datos

    private val _noteData =MutableStateFlow<List<NoteModel>>(emptyList()) //Vamos a trabajar con variables que van a trabajar en tiempo real
    val noteData : StateFlow<List<NoteModel>> = _noteData

    var state by mutableStateOf(NoteModel())
        private set

    fun onValue(value:String,text:String){
        when(text){
            "title"->state =state.copy(title = value)
            "description"->state=state.copy(description = value)
        }
    }


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

    fun getNoteId(idNote:String){
        firestore.collection("Notes")
            .document(idNote)
            .addSnapshotListener { snapshot, error ->
                if (snapshot!=null){
                    val note = snapshot.toObject(NoteModel::class.java)
                    state = state.copy(
                        title = note?.title?:"",
                        description = note?.description?:""

                    )
                }
            }
    }

    fun editNote(idNote: String, onSuccess: () -> Unit){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val editNote = hashMapOf(
                    "title" to state.title,
                    "description" to state.description
                )

                firestore.collection("Notes").document(idNote)
                    .update(editNote as Map<String,Any>)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener {e->
                        Log.d("Error", "${e.message}")
                    }
            }catch (e:Exception){
                Log.d("Error al editar", "${e.message}")
            }
        }
    }

    fun getNotes(){
        val email = auth.currentUser?.email
        firestore.collection("Notes")
            .whereEqualTo("email",email.toString())
            .orderBy("fecha",Query.Direction.DESCENDING)
            .addSnapshotListener{query,error ->
                if(error!=null){
                    return@addSnapshotListener
                }
                val notes = mutableListOf<NoteModel>()
                if(query!=null){
                    for(note in query){
                        val myNote = note.toObject(NoteModel::class.java)
                            .copy(idNote = note.id)
                        notes.add(myNote)
                    }
                }
                _noteData.value=notes
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