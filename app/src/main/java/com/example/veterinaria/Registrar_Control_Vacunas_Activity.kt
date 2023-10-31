package com.example.veterinaria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.veterinaria.Adaptadores.ControlesAdaptador
import com.example.veterinaria.Adaptadores.ControlesAdaptadorListener
import com.example.veterinaria.DataBase.DB
import com.example.veterinaria.DataBase.Entities.ControlVacunas
import com.example.veterinaria.DataBase.Entities.Mascotas
import com.example.veterinaria.DataBase.Entities.Vacunas
import com.example.veterinaria.databinding.ActivityRegistrarControlVacunasBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class Registrar_Control_Vacunas_Activity : AppCompatActivity(), ControlesAdaptadorListener {
    lateinit var binding: ActivityRegistrarControlVacunasBinding
    lateinit var listaMascotas: MutableList<Mascotas>
    lateinit var listaVacunas: MutableList<Vacunas>
    lateinit var mascotaSelect: Mascotas
    lateinit var vacunaSelect: Vacunas
    lateinit var adaptador: ControlesAdaptador
    lateinit var listaControles: MutableList<ControlVacunas>
    lateinit var controlVacunas: ControlVacunas
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegistrarControlVacunasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvControles.layoutManager = LinearLayoutManager(this)
        LlenarMascotaSp()
        LlenarVacunasSp()
        ValidarGuardar()
    }

    fun LlenarMascotaSp() {
        lifecycleScope.launch {
            listaMascotas = DB.ObtenerDB(this@Registrar_Control_Vacunas_Activity).MascotaDao()
                .ObtenerTodoMascotas()
            var adaptador = object : ArrayAdapter<Mascotas>(
                this@Registrar_Control_Vacunas_Activity,
                android.R.layout.simple_spinner_dropdown_item,
                listaMascotas
            ) {
                override fun getItemId(position: Int): Long {
                    return getItem(position)?.id ?: 0
                }
            }
            binding.spMascotas.adapter = adaptador
            binding.spMascotas.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        mascotaSelect = binding.spMascotas.selectedItem as Mascotas
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }
                }
        }
    }

    fun LlenarVacunasSp() {
        lifecycleScope.launch {
            listaVacunas =
                DB.ObtenerDB(this@Registrar_Control_Vacunas_Activity).VacunasDao().ObtenerTodo()
            var adaptador = object : ArrayAdapter<Vacunas>(
                this@Registrar_Control_Vacunas_Activity,
                android.R.layout.simple_spinner_dropdown_item,
                listaVacunas
            ) {
                override fun getItemId(position: Int): Long {
                    return (getItem(position)?.id ?: 0).toLong()
                }
            }
            binding.spVacunas.adapter = adaptador
            binding.spVacunas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    vacunaSelect = binding.spVacunas.selectedItem as Vacunas
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }

    fun ValidarGuardar() {
        binding.apply {
            val dateFormat = SimpleDateFormat("d MMM yyyy EEE HH:mm:ss")
            val date = dateFormat.format(Date())
            btnAgregarControl.setOnClickListener {
                if (btnAgregarControl.text.equals("Agregar")) {
                    controlVacunas = ControlVacunas(
                        0,
                        mascotaSelect.id,
                        vacunaSelect.id,
                        date
                    )
                    AgregarControl(controlVacunas)
                    Toast.makeText(
                        this@Registrar_Control_Vacunas_Activity,
                        "Guardo con exito",
                        Toast.LENGTH_SHORT
                    ).show()
                }else if (btnAgregarControl.text.equals("Actualizar")){
                    controlVacunas.vacunaid=vacunaSelect.id
                    controlVacunas.mascotaid=mascotaSelect.id
                    controlVacunas.fecha=date
                    ActualizarControl(controlVacunas)
                    Toast.makeText(this@Registrar_Control_Vacunas_Activity, "Actualizado", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun ActualizarControl(controlVacunas: ControlVacunas) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registrar_Control_Vacunas_Activity).ControlVacunaDao().ActualizarControl(controlVacunas.id,controlVacunas.mascotaid, controlVacunas.vacunaid,controlVacunas.fecha)
            ObtenerControl()
            LimpiarCampos()
        }
    }

    fun ObtenerControl() {
        lifecycleScope.launch {
            listaControles =
                DB.ObtenerDB(this@Registrar_Control_Vacunas_Activity).ControlVacunaDao()
                    .ObtenerControlVacunas()
            adaptador = ControlesAdaptador(listaControles, this@Registrar_Control_Vacunas_Activity)
            binding.rvControles.adapter = adaptador
        }
    }

    fun AgregarControl(controlVacunas: ControlVacunas) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registrar_Control_Vacunas_Activity).ControlVacunaDao()
                .AgregarControl(controlVacunas)
            ObtenerControl()
            LimpiarCampos()
        }
    }

    private fun LimpiarCampos() {
        controlVacunas.id = 0
        controlVacunas.fecha = ""
        controlVacunas.mascotaid = 0
        controlVacunas.vacunaid = 0
        if (binding.btnAgregarControl.text.equals("Actualizar")) {
            binding.btnAgregarControl.setText("Agregar")
        }
    }

    override fun onDeleteItemClick(controlVacunas: ControlVacunas) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registrar_Control_Vacunas_Activity).ControlVacunaDao().EliminarControl(controlVacunas.id)
            adaptador.notifyDataSetChanged()
            ObtenerControl()
            Toast.makeText(this@Registrar_Control_Vacunas_Activity, "Eliminado con Exito", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onEditItemClick(controlVacunas: ControlVacunas) {
        binding.btnAgregarControl.setText("Actualizar")
        this.controlVacunas=controlVacunas
    }

}