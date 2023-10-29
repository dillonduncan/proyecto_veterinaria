package com.example.veterinaria.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.veterinaria.DataBase.Entities.Usuario

@Dao
interface UsuarioDao {
    @Query("SELECT id FROM tabla_usuario WHERE ${Usuario.COL_ID}=:idRol")
    suspend fun ObtenerIdRolForId(idRol: Long):Long
    @Query("SELECT * FROM tabla_usuario WHERE id=:idUser")
    suspend fun ObtenerUsuarioForid(idUser: Long): Usuario
    @Query("SELECT id FROM tabla_usuario WHERE contraseña=:contra")
    suspend fun ObtenerIdContraseña(contra: Int): Long
    @Query("SELECT id FROM tabla_usuario WHERE nombre_usuario=:nombre")
    suspend fun ObtenerIdNombre(nombre: String): Long
    @Query("SELECT * FROM tabla_usuario")
    suspend fun ObtenerTodoUsuario(): MutableList<Usuario>
    @Insert
    suspend fun InsertarUsuario(vararg usuario: Usuario)
    @Query("DELETE FROM tabla_usuario WHERE ${Usuario.COL_ID}=:id")
    suspend fun EliminarUsuario(id: Long)
    @Query("UPDATE ${Usuario.TABLE_NAME} SET ${Usuario.COL_NOMBRE}=:nombreUser,${Usuario.COL_CORREO}=:correo,${Usuario.COL_CONTRASENA}=:contraseña,${Usuario.COL_IDROL}=:idrol WHERE ${Usuario.COL_ID}=:id")
    suspend fun ActualizarUsuario(
        id: Long,
        nombreUser: String,
        correo: String,
        contraseña: Int,
        idrol: Long
    )
}
