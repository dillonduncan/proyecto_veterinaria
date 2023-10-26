package com.example.veterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.veterinaria.databinding.ActivityRegistroVacunasBinding
import com.example.veterinaria.databinding.ActivityRegistrosPrincipalBinding

class Registros_Principal_Activity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrosPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrosPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        RegistroMascota()
        RegistroRaza()
        RegistroTipo()
        RegistroVacunas()
        RegistroRoles()
        RegistroUsuario()
        RegistrarControles()
        LlenarDatosUser()
        CerrarSesion()
    }
    fun LlenarDatosUser(){
        binding.txtNombreUsuario.text=login_Activity.usuario.nombre
        binding.txtCorreoUsuario.text=login_Activity.usuario.correo
    }
    fun CerrarSesion(){
        binding.txtCerrarSesion.setOnClickListener { startActivity(Intent(this@Registros_Principal_Activity,login_Activity::class.java)) }
    }
    fun RegistrarControles()=binding.btnControlVacunas.setOnClickListener { startActivity(Intent(this@Registros_Principal_Activity,Registrar_Control_Vacunas_Activity::class.java)) }
    fun RegistroRoles()=binding.btnRegistrarRoles.setOnClickListener { startActivity(Intent(this@Registros_Principal_Activity,Registrar_Roles_Activity::class.java)) }
    fun RegistroUsuario()=binding.btnRegistrarUsuarios.setOnClickListener { startActivity(Intent(this@Registros_Principal_Activity, Registro_Usuario_Activity::class.java)) }
    fun RegistroMascota()=binding.btnRgstrMascota.setOnClickListener {startActivity(Intent(this@Registros_Principal_Activity,Registro_Mascota_Activity::class.java)) }
    fun RegistroTipo()=binding.btnRgstrTipoMascotas.setOnClickListener {startActivity(Intent(this@Registros_Principal_Activity,Registro_Tipos_Activity::class.java)) }
    fun RegistroVacunas()=binding.btnRgstrVacuna.setOnClickListener {startActivity(Intent(this@Registros_Principal_Activity,Registro_Vacunas_Activity::class.java)) }
    fun RegistroRaza()=binding.btnRgstrRazaMascotas.setOnClickListener {startActivity(Intent(this@Registros_Principal_Activity,Registro_Raza_Activity::class.java)) }
}