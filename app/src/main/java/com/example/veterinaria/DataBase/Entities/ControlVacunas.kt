package com.example.veterinaria.DataBase.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.NO_ACTION
import androidx.room.PrimaryKey

@Entity(tableName = ControlVacunas.TABLE_NAME, foreignKeys = [ForeignKey(
    entity = Mascotas::class,
    parentColumns = ["${Mascotas.COL_ID}"],
    childColumns = ["${ControlVacunas.COL_MASCOTAID}"],
    onDelete = NO_ACTION,
    onUpdate = NO_ACTION
),ForeignKey(
    entity = Vacunas::class,
    parentColumns = ["${Vacunas.COL_ID}"],
    childColumns = ["${ControlVacunas.COL_VACUNAID}"],
    onUpdate = NO_ACTION,
    onDelete = NO_ACTION
)])
data class ControlVacunas(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COL_ID)var id:Long,
    @ColumnInfo(name = COL_MASCOTAID)var mascotaid:Long,
    @ColumnInfo(name = COL_VACUNAID)var vacunaid:Long,
    @ColumnInfo(name = COL_FECHA)var fecha:String
) {
    companion object{
        const val TABLE_NAME="tabla_control_vacunas"
        const val COL_ID="id"
        const val COL_MASCOTAID="mascota_id"
        const val COL_VACUNAID="vacuna_id"
        const val COL_FECHA="Fecha"
    }
}