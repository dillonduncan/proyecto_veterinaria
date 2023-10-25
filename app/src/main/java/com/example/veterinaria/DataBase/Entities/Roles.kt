package com.example.veterinaria.DataBase.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(tableName = Roles.TABLE_NAME, indices = [Index(value = [Roles.COL_NOMBRE], unique = true)])
data class Roles(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_ID)var id:Long,
    @ColumnInfo(name= COL_NOMBRE)var nombre:String
) {
    override fun toString(): String {
        return "$nombre"
    }

    companion object{
        const val TABLE_NAME="tabla_roles"
        const val COL_ID="id"
        const val COL_NOMBRE="nombre_rol"
    }
}