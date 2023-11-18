package com.example.veterinaria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.veterinaria.DataBase.DB
import com.example.veterinaria.DataBase.Entities.Usuario
import com.example.veterinaria.databinding.ActivityRegistrarceUsuarioBinding
import kotlinx.coroutines.launch

class Registrarce_Usuario_Activity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrarceUsuarioBinding
    lateinit var usuario: Usuario
    lateinit var listUsuarios: List<String>
    var idUser: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarceUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ValidarGuardar()
        ObtenerUsuarios()
    }

    fun ObtenerUsuarios() {
        lifecycleScope.launch {
            listUsuarios =
                DB.ObtenerDB(this@Registrarce_Usuario_Activity).UsuarioDao().ObtenerNombreUsuarios()
        }
    }

    fun ValidarGuardar() {
        binding.apply {
            btnRegistrarse.setOnClickListener {
                if (edtNameUser.text.isNullOrEmpty()) {
                    edtNameUser.error = "Campo obligario"
                }
                if (edtAdressUser.text.isNullOrEmpty()) {
                    edtAdressUser.error = "Campo obligario"
                }
                if (edtPassword.text.isNullOrEmpty()) {
                    edtPassword.error = "Campo obligario"
                } else if (edtNameUser.text!!.isNotEmpty() && edtPassword.text!!.isNotEmpty() && edtAdressUser.text!!.isNotEmpty()) {
                    var nombre = ""
                    listUsuarios.forEach { name ->
                        if (name == binding.edtNameUser.text.toString()) {
                            binding.edtNameUser.error = "Este nombre de usuario ya existe"
                            nombre = name
                        }
                    }
                    if (nombre != binding.edtNameUser.text.toString()) {
                        usuario = Usuario(
                            0,
                            edtNameUser.text.toString(),
                            edtAdressUser.text.toString(),
                            edtPassword.text.toString().toInt(),
                            2
                        )
                        AgregarUsuario(usuario)
                        Toast.makeText(
                            this@Registrarce_Usuario_Activity,
                            "Registro Exitoso",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    fun AgregarUsuario(usuario: Usuario) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registrarce_Usuario_Activity).UsuarioDao().InsertarUsuario(usuario)
            LimpiarCampos()
            idUser = DB.ObtenerDB(this@Registrarce_Usuario_Activity).RolesDao()
                .ObtenerIdRolForName("Usuario")
        }
    }

    private fun LimpiarCampos() {
        binding.apply {
            edtAdressUser.setText("")
            edtPassword.setText("")
            edtNameUser.setText("")
        }
    }
}