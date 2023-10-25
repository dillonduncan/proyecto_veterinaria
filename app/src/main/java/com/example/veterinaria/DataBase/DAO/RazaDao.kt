package com.example.veterinaria.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.veterinaria.DataBase.Entities.Razas

@Dao
interface RazaDao {
    @Query("SELECT * FROM tabla_razas")
    suspend fun ObtenerTodoRaza():MutableList<Razas>
    /*@Query("SELECT Tipo_Raza FROM tabla_razas WHERE Nombre_Raza=:nom_raza")
    suspend fun ObtenerTipoRazaNom(nom_raza:String):MutableList<String>*/
    /*@Query("SELECT Nombre_Raza FROM tabla_razas")
    suspend fun ObtenerRazas():MutableList<String>*/
   /* @Query("SELECT * FROM tabla_razas")
    suspend fun hsdh():MutableList<Razas>*/
    @Query("SELECT * FROM tabla_razas WHERE tipo_Raza_id=:tipoRz")
    suspend fun ObtenerRaza_Tipo(vararg tipoRz:Long):MutableList<Razas>
    @Insert
    suspend fun AgregarRaza(vararg razas: Razas)
    @Query("DELETE FROM tabla_razas WHERE id=:id")
    suspend fun EliminarRaza(id:Long)
    @Query("UPDATE tabla_razas SET Nombre_Raza=:nombreRz,tipo_Raza_id=:tipoRz WHERE id=:id")
    suspend fun ActualizarRaza(nombreRz:String,tipoRz:Long,id:Long)
    @Query("SELECT ${Razas.COL_NAME} FROM tabla_razas WHERE ${Razas.COL_ID}=:idRaza")
    suspend fun ObtenerNombreRaza_Id(idRaza:Long):MutableList<String>
}