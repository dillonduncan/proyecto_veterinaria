package com.example.veterinaria.Adaptadores

import com.example.veterinaria.DataBase.Entities.Razas

interface RazasAdaptadorListener {
    fun onDeleteItemClick(razas: Razas)
    fun onEditItemClick(razas:Razas)
}