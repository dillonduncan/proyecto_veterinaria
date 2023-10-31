package com.example.veterinaria.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.veterinaria.DataBase.Entities.ControlVacunas

@Dao
interface ControlVacunaDao {
    @Query("SELECT * FROM tabla_control_vacunas")
    suspend fun ObtenerControlVacunas():MutableList<ControlVacunas>
    @Insert
    suspend fun AgregarControl(vararg controlVacunas: ControlVacunas)
    @Query("DELETE FROM tabla_control_vacunas WHERE id=:Id")
    suspend fun EliminarControl(Id:Long)
    @Query("UPDATE tabla_control_vacunas SET mascota_id=:IdMascota,vacuna_id=:VacunaId,Fecha=:fecha WHERE ${ControlVacunas.COL_ID}=:idControl")
    suspend fun ActualizarControl(idControl:Long,IdMascota:Long,VacunaId:Long,fecha:String)
}