package com.example.veterinaria.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.veterinaria.DataBase.Entities.Vacunas

@Dao
interface VacunasDao {
    @Query("SELECT * FROM ${Vacunas.TABLE_NAME}")
    suspend fun ObtenerTodo():MutableList<Vacunas>
    @Insert
    suspend fun AgregarVacuna(vararg vacunas: Vacunas)
    @Query("DELETE FROM tabla_vacunas WHERE ${Vacunas.COL_ID}=:id")
    suspend fun EliminarVacuna(id:Long)
    @Query("UPDATE tabla_vacunas SET ${Vacunas.COL_NAME}=:nombre WHERE ${Vacunas.COL_ID}=:id")
    suspend fun ActualizarVacuna(id:Long,nombre:String)
}