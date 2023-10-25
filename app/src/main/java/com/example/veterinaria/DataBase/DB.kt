package com.example.veterinaria.DataBase

import android.content.ContentValues
import android.content.Context
import androidx.room.Database
import androidx.room.OnConflictStrategy
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.veterinaria.DataBase.DAO.ControlVacunaDao
import com.example.veterinaria.DataBase.DAO.MascotaDao
import com.example.veterinaria.DataBase.DAO.RazaDao
import com.example.veterinaria.DataBase.DAO.RolesDao
import com.example.veterinaria.DataBase.DAO.TiposDao
import com.example.veterinaria.DataBase.DAO.UsuarioDao
import com.example.veterinaria.DataBase.DAO.VacunasDao
import com.example.veterinaria.DataBase.Entities.ControlVacunas
import com.example.veterinaria.DataBase.Entities.Mascotas
import com.example.veterinaria.DataBase.Entities.Razas
import com.example.veterinaria.DataBase.Entities.Roles
import com.example.veterinaria.DataBase.Entities.Tipos
import com.example.veterinaria.DataBase.Entities.Usuario
import com.example.veterinaria.DataBase.Entities.Vacunas

@Database(entities = [Tipos::class,Vacunas::class,Roles::class,Usuario::class,Razas::class,Mascotas::class,ControlVacunas::class], version = 10)
abstract class DB:RoomDatabase() {
    abstract fun TiposDao():TiposDao
    abstract fun VacunasDao():VacunasDao
    abstract fun RazaDao():RazaDao
    abstract fun MascotaDao():MascotaDao
    abstract fun UsuarioDao():UsuarioDao
    abstract fun RolesDao():RolesDao
    abstract fun ControlVacunaDao():ControlVacunaDao
    companion object{
        @Volatile
        private var INSTANCIA: DB?=null
        fun ObtenerDB(context: Context):DB{
            if (INSTANCIA!=null){
                return INSTANCIA!!
            }
           /* if (INSTANCIA==null && context==null){
                throw Exception("Debe pasarle contexto")
            }*/
            if (context!=null){
                INSTANCIA= Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "BD"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        var listRoles= listOf(
                            Roles(0,"Administrador"),
                            Roles(0,"Usuario")
                        )
                        listRoles.forEach {
                            rol->
                            db.insert(Roles.TABLE_NAME,OnConflictStrategy.ABORT, ContentValues().apply {
                                put(Roles.COL_NOMBRE,rol.nombre)
                            })
                        }
                    }
                }).build()
            }
            return INSTANCIA!!
        }
    }
}