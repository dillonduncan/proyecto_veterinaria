package com.example.veterinaria.DataBase.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.NO_ACTION
import androidx.room.PrimaryKey

@Entity(tableName = Razas.TABLE_NAME, foreignKeys = [ForeignKey(
    entity = Tipos::class,
    parentColumns = ["${Tipos.COL_ID}"],
    childColumns = ["${Razas.COL_TIPOID}"],
    onDelete = NO_ACTION,
    onUpdate = NO_ACTION
)])
data class Razas (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_ID)var id_raza:Long,
    @ColumnInfo(name = COL_NAME)var nombre_raza:String,
    @ColumnInfo(name = COL_TIPOID)var tipo_Raza_id:Long){
    companion object{
        const val TABLE_NAME="tabla_razas"
        const val COL_ID="id"
        const val COL_NAME="nombre_raza"
        const val COL_TIPOID="tipo_Raza_id"
    }
    override fun toString(): String {
        return "$nombre_raza"
    }
}