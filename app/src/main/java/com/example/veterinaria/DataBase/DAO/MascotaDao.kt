package com.example.veterinaria.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.veterinaria.DataBase.Entities.Mascotas

@Dao
interface MascotaDao {
     @Query("SELECT * FROM tabla_mascotas")
     suspend fun ObtenerTodoMascotas():MutableList<Mascotas>
     @Insert
     suspend fun InsertarMascota(vararg mascotas: Mascotas)
     @Query("DELETE FROM tabla_mascotas WHERE ID=:id")
     suspend fun EliminarMascota(id:Long)
     @Query("UPDATE tabla_mascotas SET Nombre_Mascota=:nombre_masct,Fecha_Nacimiento=:fecha_nact,Tipo_Mascota_id=:tipo_msct,Raza_Mascota_id=:raza_msct WHERE ID=:id")
     suspend fun ActualizarMascota(id:Long,nombre_masct:String,fecha_nact:String,tipo_msct:Long,raza_msct:Long)
}