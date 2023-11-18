package com.example.veterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.veterinaria.Adaptadores.RazasAdaptador
import com.example.veterinaria.Adaptadores.RazasAdaptadorListener
import com.example.veterinaria.Adaptadores.TiposAdaptador
import com.example.veterinaria.DataBase.DB
import com.example.veterinaria.DataBase.Entities.Razas
import com.example.veterinaria.DataBase.Entities.Roles
import com.example.veterinaria.DataBase.Entities.Tipos
import com.example.veterinaria.R
import com.example.veterinaria.databinding.ActivityRegistroRazaBinding
import kotlinx.coroutines.launch

class Registro_Raza_Activity : AppCompatActivity(), RazasAdaptadorListener {
    lateinit var binding: ActivityRegistroRazaBinding
    lateinit var listaTipos: MutableList<Tipos>
    lateinit var tipos: Tipos
    lateinit var adaptador: RazasAdaptador
    lateinit var razas: Razas
    lateinit var tipoSelect: Tipos
    var idTipoSelect = 0
    var listamsctRazas: MutableList<Razas> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroRazaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvRazaMascota.layoutManager = LinearLayoutManager(this)
        LlenarSpinner()
        ValidarGuardar()
        //binding.edtRaza.setText(login_Activity.usuario.nombre)
        binding.btnVerRegistros.setOnClickListener { ObtenerRaza() }
        binding.btnAtras.setOnClickListener {
            startActivity(
                Intent(
                    this@Registro_Raza_Activity,
                    Registros_Principal_Activity::class.java
                )
            )
        }
    }

    fun ValidarGuardar() {
        binding.btnAgregarRaza.setOnClickListener {
            if (binding.edtRaza.text.isNullOrEmpty()) {
                binding.edtRaza.error = "Campo Obligatorio"
                return@setOnClickListener
            }
            if (binding.btnAgregarRaza.text.equals("Agregar")) {
                razas = Razas(
                    0,
                    binding.edtRaza.text.toString().trim(),
                    tipoSelect.id
                )
                AgregarRaza(razas)
                Toast.makeText(this, "Guardo con exito", Toast.LENGTH_SHORT).show()
            } else if (binding.btnAgregarRaza.text.equals("Actualizar")) {
                razas.nombre_raza = binding.edtRaza.text.toString().trim()
                razas.tipo_Raza_id = tipoSelect.id
                ActualizarRaza(razas)
            }
        }
    }

    /*fun LlenarSp(){
        lifecycleScope.launch {
            var lisTipos=DB.ObtenerDB(this@Registro_Raza_Activity).TiposDao().Obtener_Tipos()
            runOnUiThread {
                var adaptador = object : ArrayAdapter<Tipos>(
                    this@Registro_Raza_Activity,
                    android.R.layout.simple_spinner_dropdown_item,
                    lisTipos
                ) {
                    override fun getItemId(position: Int): Long {
                        return getItemId(position)
                    }
                }
            }
        }
    }*/
    fun LlenarSpinner() {
        lifecycleScope.launch {
            listaTipos = DB.ObtenerDB(this@Registro_Raza_Activity).TiposDao().Obtener_Tipos()
            /*var Adaptador=object :ArrayAdapter<Tipos>(this@Registro_Raza_Activity,android.R.layout.simple_spinner_item,listaTipos){
            }*/
            if (listaTipos.size == 0) {
                var tipoPerro = Tipos(0, "Perro")
                DB.ObtenerDB(this@Registro_Raza_Activity).TiposDao().AgregarTipo(tipoPerro)
                var tipoGato = Tipos(0, "Gato")
                DB.ObtenerDB(this@Registro_Raza_Activity).TiposDao().AgregarTipo(tipoGato)
                listaTipos = DB.ObtenerDB(this@Registro_Raza_Activity).TiposDao().Obtener_Tipos()
            }
            var adapter = object : ArrayAdapter<Tipos>(
                this@Registro_Raza_Activity,
                android.R.layout.simple_spinner_dropdown_item,
                listaTipos
            ) {
                override fun getItemId(position: Int): Long {
                    return getItem(position)?.id ?: 0
                }
            }
            binding.spTiposRaza.adapter = adapter
            binding.spTiposRaza.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    tipoSelect = binding.spTiposRaza.selectedItem as Tipos
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }

    fun ObtenerRaza() {
        lifecycleScope.launch {
            listamsctRazas = DB.ObtenerDB(this@Registro_Raza_Activity).RazaDao().ObtenerTodoRaza()
            adaptador = RazasAdaptador(listamsctRazas, this@Registro_Raza_Activity)
            binding.rvRazaMascota.adapter = adaptador
        }
    }

    fun AgregarRaza(razas: Razas) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Raza_Activity).RazaDao().AgregarRaza(razas)
            LimpiarCampos()
        }
    }

    private fun LimpiarCampos() {
        razas.nombre_raza = ""
        razas.tipo_Raza_id = 0
        razas.id_raza = 0
        binding.edtRaza.setText("")
        if (binding.btnAgregarRaza.text.equals("Actualizar")) {
            binding.btnAgregarRaza.setText("Agregar")
            binding.edtRaza.isEnabled = true
        }
    }

    fun ActualizarRaza(razas: Razas) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Raza_Activity).RazaDao()
                .ActualizarRaza(razas.nombre_raza, razas.tipo_Raza_id, razas.id_raza)
            ObtenerRaza()
            LimpiarCampos()
        }
    }

    override fun onDeleteItemClick(razas: Razas) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Raza_Activity).RazaDao().EliminarRaza(razas.id_raza)
            adaptador.notifyDataSetChanged()
            ObtenerRaza()
            Toast.makeText(this@Registro_Raza_Activity, "Eliminado con Exito", Toast.LENGTH_SHORT)
                .show()
        }
        /*AlertDialog.Builder(this@Registro_Raza_Activity)
            .setMessage("¿Desea Eliminar?")
            .setPositiveButton("Si"){
                view,b->
                DltRaza()
            }
            .setNegativeButton("No"){
                    _, _ ->
                Toast.makeText(this@Registro_Raza_Activity, "Se cancelo", Toast.LENGTH_SHORT).show()
            }.create().show()*/
    }

    /*fun DltRaza(){
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Raza_Activity).RazaDao().EliminarRaza(razas.id_raza)
            adaptador.notifyDataSetChanged()
            ObtenerRaza()
            Toast.makeText(this@Registro_Raza_Activity, "Eliminado con Exito", Toast.LENGTH_SHORT).show()
        }
    }*/
    override fun onEditItemClick(razas: Razas) {
        binding.btnAgregarRaza.setText("Actualizar")
        this.razas = razas
        binding.edtRaza.setText(this.razas.nombre_raza)
        /*binding.btnAgregarRaza.setOnClickListener {
            Toast.makeText(this, "Actualizado con exito", Toast.LENGTH_SHORT).show()
        }*/
    }
}