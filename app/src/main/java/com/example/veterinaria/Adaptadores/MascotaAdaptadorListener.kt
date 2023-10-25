package com.example.veterinaria.Adaptadores

import com.example.veterinaria.DataBase.Entities.Mascotas
import com.example.veterinaria.DataBase.Entities.Razas

interface MascotaAdaptadorListener {
    fun onDeleteItemClick(mascotas: Mascotas)
    fun onEditItemClick(mascotas: Mascotas)
}