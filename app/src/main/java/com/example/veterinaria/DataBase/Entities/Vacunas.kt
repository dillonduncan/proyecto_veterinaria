package com.example.veterinaria.DataBase.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName =Vacunas.TABLE_NAME, indices = [Index(value = [Vacunas.COL_NAME], unique = true)] )
data class Vacunas(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_ID) var id:Long,
    @ColumnInfo(name = COL_NAME) var nombre_vacunas:String
) {
    override fun toString(): String {
        return "$nombre_vacunas"
    }

    companion object{
        const val TABLE_NAME="tabla_vacunas"
        const val COL_ID="id"
        const val COL_NAME="nombre_vacunas"
    }
}