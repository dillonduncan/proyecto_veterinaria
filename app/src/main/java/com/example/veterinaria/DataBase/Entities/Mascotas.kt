package com.example.veterinaria.DataBase.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.NO_ACTION
import androidx.room.PrimaryKey

@Entity(tableName = "${Mascotas.TABLE_NAME}", foreignKeys = [ForeignKey(
    entity = Tipos::class,
    parentColumns = ["${Tipos.COL_ID}"],
    childColumns = ["${Mascotas.COL_TIPOID}"],
    onDelete = NO_ACTION,
    onUpdate = NO_ACTION),
ForeignKey(
    entity = Razas::class,
    parentColumns = ["${Razas.COL_ID}"],
    childColumns = ["${Mascotas.COL_RAZAID}"],
    onDelete = NO_ACTION,
    onUpdate = NO_ACTION
)])
data class Mascotas(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_ID)var id:Long,
    @ColumnInfo(name = COL_NAME)var nombre_mascota:String,
    @ColumnInfo(name = COL_FECHA_NACIMIENTO)var fecha_nacimiento:String,
    @ColumnInfo(name = COL_TIPOID)var tipo_mascota_id:Long,
    @ColumnInfo(name = COL_RAZAID)var raza_mascota_id:Long,
    @ColumnInfo(name = COL_ID_DUEÑO)var dueño_mascota:String
){
    override fun toString(): String {
        return "$nombre_mascota"
    }
    companion object{
        const val TABLE_NAME="tabla_mascotas"
        const val COL_ID="ID"
        const val COL_NAME="Nombre_Mascota"
        const val COL_TIPOID="Tipo_Mascota_id"
        const val COL_RAZAID="Raza_Mascota_id"
        const val COL_FECHA_NACIMIENTO="Fecha_Nacimiento"
        const val COL_ID_DUEÑO="Dueño_Mascota"
    }
}
