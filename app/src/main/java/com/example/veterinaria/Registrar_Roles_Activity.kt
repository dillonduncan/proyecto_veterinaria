package com.example.veterinaria

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.veterinaria.Adaptadores.RolesAdaptador
import com.example.veterinaria.Adaptadores.RolesAdaptadorListener
import com.example.veterinaria.Adaptadores.TiposAdaptador
import com.example.veterinaria.DataBase.DB
import com.example.veterinaria.DataBase.Entities.Roles
import com.example.veterinaria.databinding.ActivityRegistrarRolesBinding
import kotlinx.coroutines.launch

class Registrar_Roles_Activity : AppCompatActivity(),RolesAdaptadorListener {
    lateinit var binding:ActivityRegistrarRolesBinding
    lateinit var listaRoles:MutableList<Roles>
    lateinit var adaptador:RolesAdaptador
    lateinit var roles:Roles

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistrarRolesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvRoles.layoutManager=LinearLayoutManager(this)
        ValidarGuardar()
        binding.btnVerRoles.setOnClickListener { ObtenerRoles() }
    }
    fun ValidarGuardar(){
        binding.btnAgregarRoles.setOnClickListener {
            if (binding.edtNombreRol.text.isNullOrEmpty()){
                binding.edtNombreRol.error="Campo Obligatorio"
                return@setOnClickListener
            }
            if (binding.btnAgregarRoles.text.equals("Agregar")){
                roles=Roles(
                    0,
                    binding.edtNombreRol.text.toString()
                )
                AgregarRol(roles)
                Toast.makeText(this, "Guardado Correctamente", Toast.LENGTH_SHORT).show()
            }else if(binding.btnAgregarRoles.text.equals("Actualizar")){
                roles.nombre=binding.edtNombreRol.text.toString()
                ActualizarRoles(roles)
            }
        }
    }
    fun ObtenerRoles(){
        lifecycleScope.launch {
            listaRoles=DB.ObtenerDB(this@Registrar_Roles_Activity).RolesDao().ObtenerTodoRoles()
            adaptador=RolesAdaptador(listaRoles,this@Registrar_Roles_Activity)
            binding.rvRoles.adapter=adaptador
        }
    }
    fun AgregarRol(roles: Roles){
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registrar_Roles_Activity).RolesDao().InsertarRol(roles)
            LimpiaCampos()
        }
    }

    private fun LimpiaCampos() {
        roles.id=0
        roles.nombre=""
        binding.edtNombreRol.setText("")
        if (binding.btnAgregarRoles.text.equals("Actualizar")){
            binding.btnAgregarRoles.setText("Agregar")
            binding.edtNombreRol.isEnabled=true
        }
    }
    fun ActualizarRoles(roles: Roles){
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registrar_Roles_Activity).RolesDao().ActualizarRol(roles.id,roles.nombre)
            ObtenerRoles()
            LimpiaCampos()
        }
    }
    override fun onDeleteItemClick(roles: Roles) {
        lifecycleScope.launch {
            DB.ObtenerDB(this@Registrar_Roles_Activity).RolesDao().EliminarRol(roles.id)
            adaptador.notifyDataSetChanged()
            ObtenerRoles()
            Toast.makeText(this@Registrar_Roles_Activity, "Eliminado con Exito", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onEditItemClick(roles: Roles) {
        binding.btnAgregarRoles.setText("Actualizar")
        this.roles=roles
        binding.edtNombreRol.setText(this.roles.nombre)
    }
}