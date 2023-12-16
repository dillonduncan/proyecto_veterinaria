package com.example.veterinaria.SharedPreferens

import android.content.Context
import com.example.veterinaria.DataBase.Entities.Usuario

class SharedPrederenService(context: Context) {
    val storage=context.getSharedPreferences("SP", 0)
    fun GuardarUserName(usuarioName: String){
        storage.edit().putString("Usuario_Name",usuarioName).apply()
    }
    fun GuardarUserCorreo(usuarioCorreo: String){
        storage.edit().putString("Usuario_Correo",usuarioCorreo).apply()
    }
    fun GuardarUserRol(usuarioRol: String){
        storage.edit().putString("Usuario_Rol",usuarioRol).apply()
    }
    fun ObtenerNombre():String{
        return storage.getString("Nombre_Usuario","").toString()
    }
    fun Borrar(){
        storage.edit().clear()
    }
}