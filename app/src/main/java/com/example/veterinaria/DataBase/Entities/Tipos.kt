package com.example.veterinaria.DataBase.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = Tipos.TABLE_NAME, indices = [Index(value = [Tipos.COL_NAME], unique = true)])
data class Tipos (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_ID)var id:Long,
    @ColumnInfo(name = COL_NAME)var nombre:String) {
    override fun toString(): String {
        return "$nombre"
    }
    companion object{
        const val TABLE_NAME="tabla_tipos"
        const val COL_ID="id"
        const val COL_NAME="nombre"
    }
}
