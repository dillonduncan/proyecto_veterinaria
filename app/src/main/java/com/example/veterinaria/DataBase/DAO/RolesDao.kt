package com.example.veterinaria.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.veterinaria.DataBase.Entities.Roles

@Dao
interface RolesDao {
    @Query("SELECT id FROM tabla_roles WHERE nombre_rol=:user")
    suspend fun ObtenerUser(user:String):Long
    @Query("SELECT id FROM tabla_roles WHERE nombre_rol=:admin")
    suspend fun ObtenerAdmin(admin:String):Long
    @Query("SELECT * FROM ${Roles.TABLE_NAME}")
    suspend fun ObtenerTodoRoles():MutableList<Roles>
    @Insert
    suspend fun InsertarRol(vararg roles: Roles)
    @Query("DELETE FROM ${Roles.TABLE_NAME} WHERE ${Roles.COL_ID}=:id")
    suspend fun EliminarRol(id:Long)
    @Query("UPDATE ${Roles.TABLE_NAME} SET ${Roles.COL_NOMBRE}=:nombreRol WHERE ${Roles.COL_ID}=:id")
    suspend fun ActualizarRol(id:Long,nombreRol:String)
}