package com.example.veterinaria.Adaptadores

import com.example.veterinaria.DataBase.Entities.Mascotas
import com.example.veterinaria.DataBase.Entities.Usuario

interface UsuariosAdaptadorListener {
    fun onDeleteItemClick(usuario: Usuario)
    fun onEditItemClick(usuario: Usuario)
}