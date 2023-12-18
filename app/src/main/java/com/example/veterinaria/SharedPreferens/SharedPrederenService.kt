package com.example.veterinaria.SharedPreferens

import android.content.Context
import com.example.veterinaria.DataBase.Entities.Usuario

class SharedPrederenService(context: Context) {
    val storage = context.getSharedPreferences("SP", Context.MODE_MULTI_PROCESS)
    fun addUserName(userName: String) {
        storage.edit().putString("usuario_name", userName).apply()
    }

    fun addUserMail(userMail: String) {
        storage.edit().putString("usuario_correo", userMail).apply()
    }

    fun addUserRol(userRole: String) {
        storage.edit().putString("usuario_rol", userRole).apply()
    }

    fun addUserStatus(status: Boolean) {
        storage.edit().putBoolean("status_user", status).apply()
    }

    fun getUserName(): String {
        return storage.getString("usuario_name", "Name").toString()
    }

    fun getUserMail(): String {
        return storage.getString("usuario_correo", "Mail").toString()
    }

    fun getRoleUser(): String {
        return storage.getString("usuario_rol", "Rol").toString()
    }

    fun getStatusUser(): Boolean {
        return storage.getBoolean("status_user", false)
    }

    fun clearSP() {
        storage.edit().clear()
    }
}