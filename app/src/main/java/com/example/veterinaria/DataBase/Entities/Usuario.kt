package com.example.veterinaria.DataBase.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.NO_ACTION
import androidx.room.PrimaryKey

@Entity(tableName = Usuario.TABLE_NAME, foreignKeys = [ForeignKey(
    entity = Roles::class,
    parentColumns = ["${Roles.COL_ID}"],
    childColumns = ["${Usuario.COL_IDROL}"],
    onDelete = NO_ACTION,
    onUpdate = NO_ACTION
)],indices = [androidx.room.Index(value = [Usuario.COL_NOMBRE], unique = true)])
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_ID)var id:Long,
    @ColumnInfo(name = COL_NOMBRE)var nombre:String,
    @ColumnInfo(name = COL_CORREO)var correo:String,
    @ColumnInfo(name = COL_CONTRASENA)var contrasena:Int,
    @ColumnInfo(name = COL_IDROL)var id_rol:Long
) {

    companion object{
        const val TABLE_NAME="tabla_usuario"
        const val COL_ID="id"
        const val COL_NOMBRE="nombre_usuario"
        const val COL_CORREO="correo"
        const val COL_CONTRASENA="contraseña"
        const val COL_IDROL="id_rol"
    }
}