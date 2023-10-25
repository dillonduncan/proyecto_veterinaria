package com.example.veterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.veterinaria.Adaptadores.TiposAdaptador
import com.example.veterinaria.Adaptadores.TiposAdaptadorListener
import com.example.veterinaria.DataBase.DB
import com.example.veterinaria.DataBase.Entities.Tipos
import com.example.veterinaria.databinding.ActivityRegistroTiposBinding
import kotlinx.coroutines.launch

class Registro_Tipos_Activity : AppCompatActivity(),TiposAdaptadorListener {
    lateinit var binding: ActivityRegistroTiposBinding
    var listamsctTipos:MutableList<Tipos> = mutableListOf()
    lateinit var adaptador: TiposAdaptador
    lateinit var tipos: Tipos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistroTiposBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvTipoMascota.layoutManager= LinearLayoutManager(this)
        ValidarGuardar()
        binding.btnAtras.setOnClickListener { startActivity(Intent(this@Registro_Tipos_Activity,Registros_Principal_Activity::class.java)) }
    }
    fun ValidarGuardar(){
        binding.btnAgregar.setOnClickListener {
            if (binding.edtNombreTipo.text.isNullOrEmpty()) {
                binding.edtNombreTipo.error="Campo Obligatorio"
                return@setOnClickListener
            }
            if (binding.btnAgregar.text.equals("Agregar")){
                tipos=Tipos(
                    0,
                    binding.edtNombreTipo.text.toString().trim()
                )
                AgregarTipo(tipos)
                Toast.makeText(this, "Guardo Correctamente", Toast.LENGTH_SHORT).show()
            }else if(binding.btnAgregar.text.equals("Actualizar")){
                tipos.nombre=binding.edtNombreTipo.text.toString().trim()
                ActualizarTipo(tipos)
            }
        }
    }
    fun ObtenerTipo(){
        lifecycleScope.launch {
            listamsctTipos= DB.ObtenerDB(this@Registro_Tipos_Activity).TiposDao().Obtener_Tipos()
            adaptador= TiposAdaptador(listamsctTipos,this@Registro_Tipos_Activity)
            binding.rvTipoMascota.adapter=adaptador
        }
    }
    fun AgregarTipo(tipos: Tipos){
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Tipos_Activity).TiposDao().AgregarTipo(tipos)
            ObtenerTipo()
            LimpiarCampos()
        }
    }
    fun LimpiarCampos(){
        tipos.nombre = ""
        tipos.id=0
        binding.edtNombreTipo.setText("")
        if(binding.btnAgregar.text.equals("Actualizar")){
            binding.btnAgregar.setText("Agregar")
            binding.edtNombreTipo.isEnabled=true
        }
    }
    fun ActualizarTipo(tipos: Tipos) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Tipos_Activity).TiposDao().ActualizarTipo(tipos.id,tipos.nombre)
            ObtenerTipo()
            LimpiarCampos()
        }
    }
    override fun onEditItemClick(tipos: Tipos) {
        binding.btnAgregar.setText("Actualizar")
        this.tipos=tipos
        binding.edtNombreTipo.setText(this.tipos.nombre)
    }
    override fun onDeleteItemClick(tipos: Tipos) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Tipos_Activity).TiposDao().EliminarTipo(tipos.id)
            adaptador.notifyDataSetChanged()
            ObtenerTipo()
            Toast.makeText(this@Registro_Tipos_Activity, "Eliminado con Exito", Toast.LENGTH_SHORT).show()
        }
    }
}