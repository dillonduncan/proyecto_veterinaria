package com.example.veterinaria.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.veterinaria.DataBase.Entities.Tipos

@Dao
interface TiposDao {
    @Query("SELECT * FROM tabla_tipos")
    suspend fun Obtener_Tipos():MutableList<Tipos>
   /* @Query("SELECT Nombre FROM tabla_tipos")
    suspend fun ObtenerNombreTipo():MutableList<String>*/
   /* @Query("SELECT ${Tipos.COL_NAME} WHERE ${Tipos.COL_ID}=:idTipo")
    suspend fun ObtenerNombreTipo_id(idTipo:Long):String*/
    @Query("SELECT ${Tipos.COL_NAME} FROM ${Tipos .TABLE_NAME} WHERE ${Tipos.COL_ID}=:idMascotaTp")
    suspend fun GetNameIdTipoMasct(idMascotaTp:Long):String
    @Insert
    suspend fun AgregarTipo(vararg tipo: Tipos)
    @Query("DELETE FROM tabla_tipos WHERE ID=:id")
    suspend fun EliminarTipo(id:Long)
    @Query("UPDATE tabla_tipos SET Nombre=:nombre WHERE ID=:id")
    suspend fun ActualizarTipo(id: Long,nombre:String)
    @Query("SELECT nombre FROM tabla_tipos WHERE id=:idTipo")
    fun ObtenerNombreTipo_Id(idTipo:Long):String
}