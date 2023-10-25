package com.example.veterinaria.Adaptadores

import com.example.veterinaria.DataBase.Entities.Tipos

interface TiposAdaptadorListener {
    fun onEditItemClick(tipos: Tipos)
    fun onDeleteItemClick(tipos: Tipos)
}