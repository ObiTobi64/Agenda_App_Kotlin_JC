package com.example.agendaapp_kotlin_jc.models

data class ContactModel(
    val myEmail:String="", //Hacemos referencia al correo del usuario el cual ha realizado el registro del contacto
    val names:String="",
    val email:String="", //Correo del contacto
    val address:String="",
    val phone:String=""

    )
