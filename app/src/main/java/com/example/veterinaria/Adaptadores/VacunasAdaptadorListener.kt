package com.example.veterinaria.Adaptadores

import com.example.veterinaria.DataBase.Entities.Tipos
import com.example.veterinaria.DataBase.Entities.Vacunas

interface VacunasAdaptadorListener {
    fun onEditItemClick(vacunas: Vacunas)
    fun onDeleteItemClick(vacunas: Vacunas)
}