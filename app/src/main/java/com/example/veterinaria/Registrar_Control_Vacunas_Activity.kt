package com.example.veterinaria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import com.example.veterinaria.DataBase.DB
import com.example.veterinaria.DataBase.Entities.Mascotas
import com.example.veterinaria.DataBase.Entities.Vacunas
import com.example.veterinaria.databinding.ActivityRegistrarControlVacunasBinding
import kotlinx.coroutines.launch

class Registrar_Control_Vacunas_Activity : AppCompatActivity() {
    lateinit var binding:ActivityRegistrarControlVacunasBinding
    lateinit var listaMascotas:MutableList<Mascotas>
    lateinit var listaVacunas:MutableList<Vacunas>
    lateinit var mascotaSelect:Mascotas
    lateinit var vacunaSelect:Vacunas
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityRegistrarControlVacunasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        LlenarMascotaSp()
        LlenarVacunasSp()
    }
    fun LlenarMascotaSp(){
        lifecycleScope.launch {
            listaMascotas=DB.ObtenerDB(this@Registrar_Control_Vacunas_Activity).MascotaDao().ObtenerTodoMascotas()
            var adaptador= object : ArrayAdapter<Mascotas>(this@Registrar_Control_Vacunas_Activity,android.R.layout.simple_spinner_dropdown_item,listaMascotas) {
                override fun getItemId(position: Int): Long {
                    return getItem(position)?.id?:0
                }
            }
            binding.spMascotas.adapter=adaptador
            binding.spMascotas.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    mascotaSelect=binding.spMascotas.selectedItem as Mascotas
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }
    fun LlenarVacunasSp(){
        lifecycleScope.launch {
            listaVacunas=DB.ObtenerDB(this@Registrar_Control_Vacunas_Activity).VacunasDao().ObtenerTodo()
            var adaptador = object : ArrayAdapter<Vacunas>(this@Registrar_Control_Vacunas_Activity,android.R.layout.simple_spinner_dropdown_item,listaVacunas) {
                override fun getItemId(position: Int): Long {
                    return (getItem(position)?.id?:0).toLong()
                }
            }
            binding.spVacunas.adapter=adaptador
            binding.spVacunas.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    vacunaSelect=binding.spVacunas.selectedItem as Vacunas
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }
    fun ObtenerControl(){
        lifecycleScope.launch {
            //DB.ObtenerDB(this@Registrar_Control_Vacunas_Activity).
        }
    }

}