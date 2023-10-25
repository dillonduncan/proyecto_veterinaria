package com.example.veterinaria.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.veterinaria.DataBase.Entities.Usuario
import com.example.veterinaria.R

class UsuariosAdaptador(
    val listausuarios:MutableList<Usuario>,
    val listener: UsuariosAdaptadorListener
):RecyclerView.Adapter<UsuariosAdaptador.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuariosAdaptador.ViewHolder {
        var vista=LayoutInflater.from(parent.context).inflate(R.layout.ly_mostrar_usuarios,parent,false)
        return ViewHolder(vista)
    }
    override fun getItemCount(): Int =listausuarios.size
    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val usuarios=listausuarios[position]
        holder.txtidUsuario.text=usuarios.id.toString()
        holder.txtNombreUsuario.text=usuarios.nombre
        holder.txtCorreoUsuario.text=usuarios.correo
        holder.txtContraseñaUsuarios.text=usuarios.contrasena.toString()
        holder.txtRol.text=usuarios.id_rol.toString()
    }
    inner class ViewHolder(ItemView:View):RecyclerView.ViewHolder(ItemView) {
        val cvUsuario=itemView.findViewById<CardView>(R.id.cvUsuarios)
        val txtidUsuario=itemView.findViewById<TextView>(R.id.txtIdUsuarios)
        val txtNombreUsuario=itemView.findViewById<TextView>(R.id.txtNombreUsuario)
        val txtCorreoUsuario=itemView.findViewById<TextView>(R.id.txtCorreo)
        val txtContraseñaUsuarios=itemView.findViewById<TextView>(R.id.txtContraseña)
        val txtRol=itemView.findViewById<TextView>(R.id.txtRol)
        val btnBorrar=itemView.findViewById<TextView>(R.id.btnBorrarUsuario)
    }
}