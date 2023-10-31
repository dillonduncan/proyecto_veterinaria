package com.example.veterinaria.DataBase.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.veterinaria.DataBase.Entities.Usuario

@Dao
interface UsuarioDao {
    @Query("SELECT ${Usuario.COL_IDROL} FROM tabla_usuario WHERE ${Usuario.COL_ID}=:idUser")
    suspend fun ObtenerIdRolForId(idUser: Long):Long
    @Query("SELECT * FROM ${Usuario.TABLE_NAME} WHERE ${Usuario.COL_ID}=:idUser")
    suspend fun ObtenerUsuarioForid(idUser: Long): Usuario
    //que me obtenga el id de la contrasena dependiendo del id del usuario obtenido si es igual al de la contrasena
    @Query("SELECT id FROM tabla_usuario WHERE contraseña=:contra")
    suspend fun ObtenerIdContraseña(contra: Int): Long
    @Query("SELECT id FROM tabla_usuario WHERE contraseña=:contra & ${Usuario.COL_ID}=:idUser")
    suspend fun ObtenerIdContraseñaForidUser(contra: Int,idUser:Int): Long
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