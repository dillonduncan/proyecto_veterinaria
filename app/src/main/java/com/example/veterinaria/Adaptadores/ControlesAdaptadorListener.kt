package com.example.veterinaria.Adaptadores

import com.example.veterinaria.DataBase.Entities.ControlVacunas
import com.example.veterinaria.DataBase.Entities.Mascotas

interface ControlesAdaptadorListener {
    fun onDeleteItemClick(controlVacunas: ControlVacunas)
    fun onEditItemClick(controlVacunas: ControlVacunas)
}