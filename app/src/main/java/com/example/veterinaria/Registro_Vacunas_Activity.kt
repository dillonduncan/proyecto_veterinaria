package com.example.veterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.veterinaria.Adaptadores.TiposAdaptador
import com.example.veterinaria.Adaptadores.VacunasAdaptador
import com.example.veterinaria.Adaptadores.VacunasAdaptadorListener
import com.example.veterinaria.DataBase.DB
import com.example.veterinaria.DataBase.Entities.Tipos
import com.example.veterinaria.DataBase.Entities.Vacunas
import com.example.veterinaria.R
import com.example.veterinaria.databinding.ActivityRegistroVacunasBinding
import kotlinx.coroutines.launch

class Registro_Vacunas_Activity : AppCompatActivity(),VacunasAdaptadorListener {
    lateinit var binding: ActivityRegistroVacunasBinding
    var listamsctVacunas:MutableList<Vacunas> = mutableListOf()
    lateinit var adaptador: VacunasAdaptador
    lateinit var vacunas: Vacunas
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistroVacunasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvVacunaMascota.layoutManager= LinearLayoutManager(this)
        ValidarGuardar()
        /*binding.btnVolver.setOnClickListener {
            startActivity(Intent(this,Registro_Mascota_Activity::class.java))
        }*/
    }
    fun ValidarGuardar(){
        binding.btnAgregarVacuna.setOnClickListener {
            if (binding.edtNombreVacuna.text.isNullOrEmpty()) {
                binding.edtNombreVacuna.error="Campo Obligatorio"
                return@setOnClickListener
            }
            if (binding.btnAgregarVacuna.text.equals("Agregar")){
                vacunas= Vacunas(
                    0,
                    binding.edtNombreVacuna.text.toString().trim()
                )
                AgregarVacuna(vacunas)
                Toast.makeText(this, "Guardo Correctamente", Toast.LENGTH_SHORT).show()
            }else if(binding.btnAgregarVacuna.text.equals("Actualizar")){
                vacunas.nombre_vacunas=binding.edtNombreVacuna.text.toString().trim()
                ActualizarVacuna(vacunas)
            }
        }
    }
    fun ObtenerVacuna(){
        lifecycleScope.launch {
            listamsctVacunas= DB.ObtenerDB(this@Registro_Vacunas_Activity).VacunasDao().ObtenerTodo()
            adaptador= VacunasAdaptador(listamsctVacunas,this@Registro_Vacunas_Activity)
            binding.rvVacunaMascota.adapter=adaptador
        }
    }
    fun AgregarVacuna(vacunas: Vacunas){
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Vacunas_Activity).VacunasDao().AgregarVacuna(vacunas)
            ObtenerVacuna()
            LimpiarCampos()
        }
    }
    fun LimpiarCampos(){
        vacunas.nombre_vacunas = ""
        vacunas.id=0
        binding.edtNombreVacuna.setText("")
        if(binding.btnAgregarVacuna.text.equals("")){
            binding.btnAgregarVacuna.setText("Agregar")
            binding.edtNombreVacuna.isEnabled=true
        }
    }
    fun ActualizarVacuna(vacunas: Vacunas) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Vacunas_Activity).VacunasDao().ActualizarVacuna(vacunas.id,vacunas.nombre_vacunas)
            ObtenerVacuna()
            LimpiarCampos()
        }
    }
    override fun onEditItemClick(vacunas: Vacunas) {
        binding.btnAgregarVacuna.setText("Actualizar")
        this.vacunas=vacunas
        binding.edtNombreVacuna.setText(this.vacunas.nombre_vacunas)
    }

    override fun onDeleteItemClick(vacunas: Vacunas) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Vacunas_Activity).VacunasDao().EliminarVacuna(vacunas.id)
            adaptador.notifyDataSetChanged()
            ObtenerVacuna()
            Toast.makeText(this@Registro_Vacunas_Activity, "Eliminado con Exito", Toast.LENGTH_SHORT).show()
        }
    }
}