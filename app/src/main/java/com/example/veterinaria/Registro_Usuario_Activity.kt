package com.example.veterinaria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.veterinaria.Adaptadores.UsuariosAdaptador
import com.example.veterinaria.Adaptadores.UsuariosAdaptadorListener
import com.example.veterinaria.DataBase.DB
import com.example.veterinaria.DataBase.Entities.Roles
import com.example.veterinaria.DataBase.Entities.Usuario
import com.example.veterinaria.databinding.ActivityRegistroUsuarioBinding
import kotlinx.coroutines.launch

class Registro_Usuario_Activity : AppCompatActivity(),UsuariosAdaptadorListener {
    lateinit var binding:ActivityRegistroUsuarioBinding
    lateinit var rolSelect:Roles
    lateinit var listaRoles:MutableList<Roles>
    lateinit var usuarios:Usuario
    lateinit var adaptador:UsuariosAdaptador
    lateinit var listUsuarios:MutableList<Usuario>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegistroUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvUsuarios.layoutManager=LinearLayoutManager(this)
        LlenarSPRoles()
        ValidarGuardar()
        binding.btnVerRegistros.setOnClickListener { ObtenerUsuario() }
    }
    fun ValidarGuardar(){
        binding.btnRegistrar.setOnClickListener {
            if (binding.edtNombreUsuario.text.isNullOrEmpty()) {
                binding.edtNombreUsuario.error = "Campo Obligatorio"
                return@setOnClickListener
            }
            if (binding.edtCorreoUsuario.text.isNullOrEmpty()) {
                binding.edtCorreoUsuario.error = "Campo Obligatorio"
                return@setOnClickListener
            }
            if (binding.edtContraseA.text.isNullOrEmpty()) {
                binding.edtContraseA.error = "Campo Obligatorio"
                return@setOnClickListener
            }
            if (binding.btnRegistrar.text.equals("Agregar")) {
                usuarios = Usuario(
                    0,
                    binding.edtNombreUsuario.text.toString(),
                    binding.edtCorreoUsuario.text.toString(),
                    binding.edtContraseA.text.toString().toInt(),
                    rolSelect.id
                )
                AgregarUsuario(usuarios)
                Toast.makeText(this, "Guardo con Exito", Toast.LENGTH_SHORT).show()
            } else if (binding.btnRegistrar.text.equals("Actualizar")){
                usuarios.nombre=binding.edtNombreUsuario.text.toString()
                usuarios.correo=binding.edtCorreoUsuario.text.toString()
                usuarios.contrasena=binding.edtContraseA.text.toString().toInt()
                usuarios.id_rol=rolSelect.id
                ActualizarUsuario(usuarios)
                Toast.makeText(this, "Actualizado", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun ObtenerUsuario() {
        lifecycleScope.launch {
            listUsuarios=DB.ObtenerDB(this@Registro_Usuario_Activity).UsuarioDao().ObtenerTodoUsuario()
            adaptador= UsuariosAdaptador(listUsuarios,this@Registro_Usuario_Activity)
            binding.rvUsuarios.adapter=adaptador
        }
    }
    fun AgregarUsuario(usuario: Usuario){
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Usuario_Activity).UsuarioDao().InsertarUsuario(usuario)
            ObtenerUsuario()
            LimpiarCampos()
        }
    }

    private fun LimpiarCampos() {
        usuarios.id=0
        usuarios.nombre=""
        usuarios.correo=""
        usuarios.contrasena=0
        usuarios.id_rol=0
        binding.edtNombreUsuario.setText("")
        binding.edtCorreoUsuario.setText("")
        binding.edtContraseA.setText("")
        if (binding.btnRegistrar.text.equals("Actualizar")){
            binding.btnRegistrar.setText("Agregar")
            binding.edtNombreUsuario.isEnabled=true
            binding.edtCorreoUsuario.isEnabled=true
            binding.edtContraseA.isEnabled=true
        }
    }
    fun ActualizarUsuario(usuario: Usuario){
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Usuario_Activity).UsuarioDao().ActualizarUsuario(usuario.id,usuario.nombre,usuario.correo,usuario.contrasena,usuario.id_rol)
            ObtenerUsuario()
            LimpiarCampos()
        }
    }
    fun LlenarSPRoles(){
        lifecycleScope.launch {
            listaRoles= DB.ObtenerDB(this@Registro_Usuario_Activity).RolesDao().ObtenerTodoRoles()
            if (listaRoles.size==0){
                var rolAdmin=Roles(0,"Administrador")
                DB.ObtenerDB(this@Registro_Usuario_Activity).RolesDao().InsertarRol(rolAdmin)
                var rolUser=Roles(0,"Usuario")
                DB.ObtenerDB(this@Registro_Usuario_Activity).RolesDao().InsertarRol(rolUser)
                listaRoles= DB.ObtenerDB(this@Registro_Usuario_Activity).RolesDao().ObtenerTodoRoles()
            }
            var adaptador = object : ArrayAdapter<Roles>(this@Registro_Usuario_Activity,android.R.layout.simple_spinner_dropdown_item,listaRoles) {
                override fun getItemId(position: Int): Long {
                    return getItem(position)?.id?:0
                }
            }
            binding.spRoles.adapter=adaptador
            binding.spRoles.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    rolSelect=binding.spRoles.selectedItem as Roles
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
    }

    override fun onDeleteItemClick(usuario: Usuario) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registro_Usuario_Activity).UsuarioDao().EliminarUsuario(usuario.id)
            adaptador.notifyDataSetChanged()
            Toast.makeText(this@Registro_Usuario_Activity, "Eliminado con Exito", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onEditItemClick(usuario: Usuario) {
        binding.btnRegistrar.setText("Actualizar")
        this.usuarios=usuario
        binding.edtNombreUsuario.setText(this.usuarios.nombre)
        binding.edtCorreoUsuario.setText(this.usuarios.correo)
        binding.edtContraseA.setText(this.usuarios.contrasena)
    }
}