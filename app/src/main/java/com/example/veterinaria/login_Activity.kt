package com.example.veterinaria

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.veterinaria.DataBase.DB
import com.example.veterinaria.DataBase.Entities.Roles
import com.example.veterinaria.DataBase.Entities.Usuario
import com.example.veterinaria.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch
import kotlin.math.log

class login_Activity : AppCompatActivity() {
    lateinit var listUsuarios: List<Usuario>
    //lateinit var listUserAdmin:List<Usuario>
   // lateinit var listRoles: List<Roles>
    lateinit var binding: ActivityLoginBinding
    var idContraseñaUsuario: Long = 0
    var idNombreUsuario: Long = 0
    var idcontrasena: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnIniciarSecion.setOnClickListener { ObtenerUsuarios() }
        binding.txtRegistrarce.setOnClickListener {
            valRegistro=1
            startActivity(
                Intent(
                    this,
                    Registro_Usuario_Activity::class.java
                )
            )
        }
    }
    fun ObtenerUsuarios() {
        try {
            lifecycleScope.launch {
                listUsuarios = DB.ObtenerDB(this@login_Activity).UsuarioDao().ObtenerTodoUsuario()
                //Toast.makeText(this@login_Activity, "$listUsuarios", Toast.LENGTH_SHORT).show()
                if (listUsuarios.size==0){
                    var user=Usuario(0,"Dillon","dillon@gmail.com",12345,1)
                    DB.ObtenerDB(this@login_Activity).UsuarioDao().InsertarUsuario(user)
                }
                listUsuarios.forEach {
                    if (binding.edtUsuario.text.isNotEmpty() && binding.edtContraseA.text.isNotEmpty() && it.nombre == binding.edtUsuario.text.toString() && it.contrasena == binding.edtContraseA.text.toString()
                            .toInt()
                    ) {
                        idNombreUsuario=0
                        idContraseñaUsuario=0
                        idcontrasena=0
                        idNombreUsuario = DB.ObtenerDB(this@login_Activity).UsuarioDao()
                            .ObtenerIdNombre(binding.edtUsuario.text.toString())
                        idContraseñaUsuario = DB.ObtenerDB(this@login_Activity).UsuarioDao()
                            .ObtenerIdContraseña(binding.edtContraseA.text.toString().toInt())
                     /*   if (idNombreUsuario.toInt()!=0){
                            idcontrasena= DB.ObtenerDB(this@login_Activity).UsuarioDao()
                                .ObtenerIdContraseñaForidUser(binding.edtContraseA.text.toString().toInt(),idNombreUsuario.toInt())
                        }*/
                       // binding.textView.text=idContraseñaUsuario.toString()+idNombreUsuario
                        if (idContraseñaUsuario == idNombreUsuario) {
                            startActivity(
                                Intent(
                                    this@login_Activity,
                                    Registros_Principal_Activity::class.java
                                )
                            )
                        }
                    }
                    if (idNombreUsuario.toInt() != 0) {
                        usuario = DB.ObtenerDB(this@login_Activity).UsuarioDao()
                            .ObtenerUsuarioForid(idNombreUsuario)
                        idRolUsuario = DB.ObtenerDB(this@login_Activity).UsuarioDao()
                            .ObtenerIdRolForId(usuario.id)
                        //Toast.makeText(this@login_Activity, "$idRolUsuario", Toast.LENGTH_SHORT).show()
                    }
                    if (binding.edtUsuario.text.isEmpty()) {
                        binding.edtUsuario.error = "Campo Obligatorio"
                    }
                    if (binding.edtContraseA.text.isEmpty()) {
                        binding.edtContraseA.error = "Campo Obligatorio"
                    }
                }
            }
        } catch (e: Exception) {
            println("error: " + e.message)
        }
    }
    companion object {
        lateinit var usuario: Usuario
        var idRolUsuario: Long = 0
        var valRegistro=0
    }
}