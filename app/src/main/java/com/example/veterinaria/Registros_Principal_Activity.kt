package com.example.veterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.marginLeft
import androidx.lifecycle.lifecycleScope
import com.example.veterinaria.DataBase.DB
import com.example.veterinaria.SharedPreferens.Shared.Companion.prefs
import com.example.veterinaria.databinding.ActivityRegistroVacunasBinding
import com.example.veterinaria.databinding.ActivityRegistrosPrincipalBinding
import kotlinx.coroutines.launch

class Registros_Principal_Activity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrosPrincipalBinding
    var idAdmin: Long = 0
    var idUser: Long = 0
    var nameRol = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrosPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Toast.makeText(this, "${prefs.getUserName()}", Toast.LENGTH_SHORT).show()
        ObtenerValidarRolUsuario()
        LlenarDatosUser()
        RegistroMascota()
        RegistroRaza()
        RegistroTipo()
        RegistroVacunas()
        RegistroRoles()
        RegistroUsuario()
        CerrarSesion()
    }

    fun ObtenerValidarRolUsuario() {
        lifecycleScope.launch {
            idAdmin = DB.ObtenerDB(this@Registros_Principal_Activity).RolesDao()
                .ObtenerIdRolForName("Administrador")
            idUser = DB.ObtenerDB(this@Registros_Principal_Activity).RolesDao()
                .ObtenerIdRolForName("Usuario")
        }
        if (login_Activity.usuario.id_rol.toInt() != 1) {
            binding.btnRegistrarRoles.isEnabled = false
            binding.btnRegistrarUsuarios.isEnabled = false
        }
    }

    fun LlenarDatosUser() {
        binding.txtNombreUsuario.text = prefs.getUserName()
        binding.txtCorreoUsuario.text = prefs.getUserMail()
    }

    fun CerrarSesion() {
        binding.txtCerrarSesion.setOnClickListener {
            startActivity(
                Intent(
                    this@Registros_Principal_Activity,
                    login_Activity::class.java
                )
            )
        }
    }

    fun RegistroRoles() = binding.btnRegistrarRoles.setOnClickListener {
        startActivity(
            Intent(
                this@Registros_Principal_Activity,
                Registrar_Roles_Activity::class.java
            )
        )
    }

    fun RegistroUsuario() = binding.btnRegistrarUsuarios.setOnClickListener {
        startActivity(
            Intent(
                this@Registros_Principal_Activity,
                Registro_Usuario_Activity::class.java
            )
        )
    }

    fun RegistroMascota() = binding.btnRgstrMascota.setOnClickListener {
        startActivity(
            Intent(
                this@Registros_Principal_Activity,
                Registro_Mascota_Activity::class.java
            )
        )
    }

    fun RegistroTipo() = binding.btnRgstrTipoMascotas.setOnClickListener {
        startActivity(
            Intent(
                this@Registros_Principal_Activity,
                Registro_Tipos_Activity::class.java
            )
        )
    }

    fun RegistroVacunas() = binding.btnRgstrVacuna.setOnClickListener {
        startActivity(
            Intent(
                this@Registros_Principal_Activity,
                Registro_Vacunas_Activity::class.java
            )
        )
    }

    fun RegistroRaza() = binding.btnRgstrRazaMascotas.setOnClickListener {
        startActivity(
            Intent(
                this@Registros_Principal_Activity,
                Registro_Raza_Activity::class.java
            )
        )
    }
}