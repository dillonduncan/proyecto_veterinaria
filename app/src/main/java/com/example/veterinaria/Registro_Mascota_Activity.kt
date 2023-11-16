package com.example.veterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.graphics.isWideGamut
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.veterinaria.Adaptadores.MascotaAdaptador
import com.example.veterinaria.Adaptadores.MascotaAdaptadorListener
import com.example.veterinaria.Adaptadores.RazasAdaptador
import com.example.veterinaria.Adaptadores.VacunasAdaptadorListener
import com.example.veterinaria.DataBase.DB
import com.example.veterinaria.DataBase.Entities.Mascotas
import com.example.veterinaria.DataBase.Entities.Razas
import com.example.veterinaria.DataBase.Entities.Tipos
import com.example.veterinaria.databinding.ActivityRegistroMascotaBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Registro_Mascota_Activity : AppCompatActivity(), MascotaAdaptadorListener {
    lateinit var binding: ActivityRegistroMascotaBinding
    lateinit var listaTipos: MutableList<Tipos>
    lateinit var tipos: Tipos
    lateinit var tipoSelect: Tipos
    lateinit var listRz: MutableList<Razas>
    lateinit var RazaSelect: Razas
    lateinit var adaptador: MascotaAdaptador
    lateinit var mascotas: Mascotas
    var listMascotas: MutableList<Mascotas> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroMascotaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        LlenarTipoSp()
        ValidarGuardar()
        binding.rvMascota.layoutManager = LinearLayoutManager(this)
        binding.btnMostrar.setOnClickListener { ObtenerMascota() }
        binding.btnAtras.setOnClickListener {
            startActivity(
                Intent(
                    this@Registro_Mascota_Activity,
                    Registros_Principal_Activity::class.java
                )
            )
        }
        RegistrarControles()
    }
    fun RegistrarControles()=binding.btnControlVacunas.setOnClickListener { startActivity(Intent(this@Registro_Mascota_Activity,Registrar_Control_Vacunas_Activity::class.java)) }
    fun ValidarGuardar() {
        binding.btnAgregarMsct.setOnClickListener {
            if (binding.edtNombreMascota.text.isNullOrEmpty()) {
                binding.edtNombreMascota.error = "Campo obligatorio"
                return@setOnClickListener
            }
            if (binding.edtfechaNacmtMascota.text.isNullOrEmpty()) {
                binding.edtfechaNacmtMascota.error = "Campo obligatorio"
                return@setOnClickListener
            }
            if (binding.btnAgregarMsct.text.equals("Agregar")) {
                mascotas = Mascotas(
                    0,
                    binding.edtNombreMascota.text.toString(),
                    binding.edtfechaNacmtMascota.text.toString(),
                    tipoSelect.id,
                    RazaSelect.id_raza,
                    login_Activity.usuario.nombre
                )
                AgregarMascota(mascotas)
                Toast.makeText(this, "Guardo con exito", Toast.LENGTH_SHORT).show()
            } else if (binding.btnAgregarMsct.text.equals("Actualizar")) {
                mascotas.nombre_mascota = binding.edtNombreMascota.text.toString()
                mascotas.raza_mascota_id = RazaSelect.id_raza
                mascotas.fecha_nacimiento = binding.edtfechaNacmtMascota.text.toString()
                mascotas.tipo_mascota_id = tipoSelect.id
                ActualizarMascota(mascotas)
                Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun LlenarTipoSp() {
        lifecycleScope.launch {
            listaTipos = DB.ObtenerDB(this@Registro_Mascota_Activity).TiposDao().Obtener_Tipos()
            /*val Adaptador=object :ArrayAdapter<Tipos>(this@Registro_Mascota_Activity,android.R.layout.simple_spinner_item,listaTipos){
            }*/
            var adaptador = object : ArrayAdapter<Tipos>(
                this@Registro_Mascota_Activity,
                android.R.layout.simple_spinner_dropdown_item,
                listaTipos
            ) {
                override fun getItemId(position: Int): Long {
                    return getItem(position)?.id ?: 0
                }
            }
            binding.spTipos.adapter = adaptador
            binding.spTipos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    tipoSelect = binding.spTipos.selectedItem as Tipos
                    //binding.txtspinner.setText(tipoSelect)
                    LlenarRazaSp()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }

    //sobreescribir metodo tstring
    /*overray fun toString():String{
        return "$name"
    }*/
    fun LlenarRazaSp() {
        lifecycleScope.launch {
            listRz = DB.ObtenerDB(this@Registro_Mascota_Activity).RazaDao()
                .ObtenerRaza_Tipo(tipoSelect.id)
            var Adaptador = object : ArrayAdapter<Razas>(
                this@Registro_Mascota_Activity,
                android.R.layout.simple_spinner_item,
                listRz
            ) {
                override fun getItemId(position: Int): Long {
                    return (getItem(position)?.id_raza ?: 0)
                }
            }
            binding.spRazas.adapter = Adaptador
            binding.spRazas.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    RazaSelect = binding.spRazas.selectedItem as Razas
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }

    fun ObtenerMascota() {
        lifecycleScope.launch {
            listMascotas =
                DB.ObtenerDB(this@Registro_Mascota_Activity).MascotaDao().ObtenerTodoMascotas()
            adaptador = MascotaAdaptador(listMascotas, this@Registro_Mascota_Activity)
            binding.rvMascota.adapter = adaptador
        }
    }

    fun AgregarMascota(mascotas: Mascotas) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Mascota_Activity).MascotaDao().InsertarMascota(mascotas)
            ObtenerMascota()
            LimpiarCampos()
        }
    }

    private fun LimpiarCampos() {
        mascotas.nombre_mascota = ""
        mascotas.raza_mascota_id = 0
        mascotas.tipo_mascota_id = 0
        mascotas.id = 0
        binding.edtNombreMascota.setText("")
        binding.edtfechaNacmtMascota.setText("")
        if (binding.btnAgregarMsct.text.equals("Actualizar")) {
            binding.btnAgregarMsct.setText("Agregar")
            binding.edtNombreMascota.isEnabled = true
            binding.edtfechaNacmtMascota.isEnabled = true
        }
    }

    fun ActualizarMascota(mascotas: Mascotas) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Mascota_Activity).MascotaDao().ActualizarMascota(
                mascotas.id,
                mascotas.nombre_mascota,
                mascotas.fecha_nacimiento,
                mascotas.tipo_mascota_id,
                mascotas.raza_mascota_id
            )
            ObtenerMascota()
            LimpiarCampos()
        }
    }

    override fun onDeleteItemClick(mascotas: Mascotas) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Mascota_Activity).MascotaDao().EliminarMascota(mascotas.id)
            adaptador.notifyDataSetChanged()
            ObtenerMascota()
            Toast.makeText(
                this@Registro_Mascota_Activity,
                "Eliminado con exito",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onEditItemClick(mascotas: Mascotas) {
        binding.btnAgregarMsct.setText("Actualizar")
        this.mascotas = mascotas
        binding.edtfechaNacmtMascota.setText(this.mascotas.fecha_nacimiento)
        binding.edtNombreMascota.setText(this.mascotas.nombre_mascota)
    }
}