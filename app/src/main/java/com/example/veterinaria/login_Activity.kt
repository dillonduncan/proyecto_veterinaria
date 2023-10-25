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

    lateinit var listUsuarios:List<Usuario>
    //lateinit var listUserAdmin:List<Usuario>
    var idAdmin:Long=0
    var idUsuario:Long=0
    lateinit var listRoles:List<Roles>
    lateinit var binding: ActivityLoginBinding
    var idContraseñaUsuario:Long=0
    var idNombreUsuario:Long=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // ObtenerAdmin()
        binding.btnIniciarSecion.setOnClickListener { ObtenerUsuarios() }
        binding.txtRegistrarce.setOnClickListener { startActivity(Intent(this,Registro_Usuario_Activity::class.java)) }
    }
    fun ObtenerUsuarios() {
        try{
            lifecycleScope.launch {
                listUsuarios = DB.ObtenerDB(this@login_Activity).UsuarioDao().ObtenerTodoUsuario()
                Toast.makeText(this@login_Activity, "$listUsuarios", Toast.LENGTH_SHORT).show()
                listUsuarios.forEach {
                    if (binding.edtUsuario.text.isNotEmpty() && binding.edtContraseA.text.isNotEmpty() && it.nombre==binding.edtUsuario.text.toString() && it.contrasena==binding.edtContraseA.text.toString().toInt()){
                        idNombreUsuario=DB.ObtenerDB(this@login_Activity).UsuarioDao().ObtenerIdNombre(binding.edtUsuario.text.toString())
                        idContraseñaUsuario=DB.ObtenerDB(this@login_Activity).UsuarioDao().ObtenerIdContraseña(binding.edtContraseA.text.toString().toInt())
                        if (idContraseñaUsuario==idNombreUsuario){
                            startActivity(Intent(this@login_Activity,Registros_Principal_Activity::class.java))
                        }
                      //  usuario=DB.ObtenerDB(this@login_Activity).UsuarioDao().ObtenerUsuarioForid(idNombreUsuario)
                        //Toast.makeText(this@login_Activity, "$usuario", Toast.LENGTH_SHORT).show()
                       /* else if (idContraseñaUsuario!=idNombreUsuario){
                            binding.edtContraseA.error="Usuario o contraseña incorrectos"
                            binding.edtContraseA.setText("")
                        }*/
                        //buscar el id del usuario para verificar la contraseña coin el mismo id
                        //usuario tiene el mismo id de la contraseña
                    }
                    if (idNombreUsuario.toInt()!=0){
                        usuario=DB.ObtenerDB(this@login_Activity).UsuarioDao().ObtenerUsuarioForid(idNombreUsuario)
                    }
                    /*if (it.id==idNombreUsuario){
                        if (binding.edtUsuario.text.isNotEmpty() && it.nombre!=binding.edtUsuario.text.toString()){
                            Toast.makeText(this@login_Activity, "${it.id}/$idUsuario", Toast.LENGTH_SHORT).show()
                            binding.edtUsuario.error="Usuario incorrecto"
                        }
                    }
                    if (it.id==idContraseñaUsuario){
                        if (binding.edtContraseA.text.isNotEmpty() && it.contrasena!=binding.edtContraseA.text.toString().toInt()){
                            Toast.makeText(this@login_Activity, "${it.id}/$idContraseñaUsuario", Toast.LENGTH_SHORT).show()
                            binding.edtContraseA.error="Contraseña incorrecta"
                        }
                    }*/
                    if(binding.edtUsuario.text.isEmpty()){
                        binding.edtUsuario.error="Campo Obligatorio"
                    }
                    if(binding.edtContraseA.text.isEmpty()){
                        binding.edtContraseA.error="Campo Obligatorio"
                    }
                    if (binding.edtUsuario.text.isNotEmpty() && idNombreUsuario.toInt()==0){
                        binding.txtLogIncorretco.text="Usuario o contraseña incorretos"
                        Toast.makeText(this@login_Activity, "$idNombreUsuario", Toast.LENGTH_SHORT).show()
                        //binding.edtContraseA.setText("")
                    }
                    if (binding.edtContraseA.text.isNotEmpty() && idContraseñaUsuario.toInt()==0){
                        //binding.edtContraseA.setText("")
                        Toast.makeText(this@login_Activity, "$idContraseñaUsuario", Toast.LENGTH_SHORT).show()
                        binding.txtLogIncorretco.text="Usuario o contraseña incorretos"
                    }
                  /*  if (binding.edtUsuario.text.isNotEmpty() && usuario.nombre!=binding.edtUsuario.text.toString()){
                        binding.edtUsuario.error="Usuario incorrecto"
                    }
                    if (binding.edtContraseA.text.isNotEmpty() && usuario.contrasena!=binding.edtContraseA.text.toString().toInt()){
                        binding.edtContraseA.error="Contraseña incorrecta"
                    }*/
                    /*if(binding.edtUsuario.text.isNotEmpty() && binding.edtUsuario.text.toString()!=it.nombre){
                        binding.edtUsuario.error="Usuario incorrecto"
                    }
                    if(binding.edtContraseA.text.isNotEmpty() && binding.edtContraseA.text.toString().toInt()!=it.contrasena){
                        binding.edtContraseA.error="Contraseña incorrecta"
                    }*/
                }

        }}catch (e:Exception){
            println("error: "+e.message)
        }
    }
    companion object{
        lateinit var usuario:Usuario
    }
    fun IniciarSecion(){

    }
    fun ObtenerAdmin(){
        lifecycleScope.launch {
            listRoles=DB.ObtenerDB(this@login_Activity).RolesDao().ObtenerTodoRoles()
            Toast.makeText(this@login_Activity, "$listRoles", Toast.LENGTH_SHORT).show()
            idUsuario=DB.ObtenerDB(this@login_Activity).RolesDao().ObtenerUser("usuario")
            Toast.makeText(this@login_Activity, "$idUsuario", Toast.LENGTH_SHORT).show()
            idAdmin=DB.ObtenerDB(this@login_Activity).RolesDao().ObtenerAdmin("administrador")
            Toast.makeText(this@login_Activity,"$idAdmin", Toast.LENGTH_SHORT).show()
        }
    }
}