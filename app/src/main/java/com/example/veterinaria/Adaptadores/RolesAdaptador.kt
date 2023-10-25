package com.example.veterinaria.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.veterinaria.DataBase.Entities.Roles
import com.example.veterinaria.R
import com.example.veterinaria.Registrar_Roles_Activity

class RolesAdaptador(
    val listaroles:MutableList<Roles>,
    val listener:RolesAdaptadorListener
):RecyclerView.Adapter<RolesAdaptador.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var vista=LayoutInflater.from(parent.context).inflate(R.layout.ly_mostrar_roles,parent,false)
        return ViewHolder(vista)
    }
    override fun getItemCount(): Int =listaroles.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val roles=listaroles[position]
        holder.txtid.text=roles.id.toString()
        holder.txtNombreRol.text=roles.nombre
        holder.cvRoles.setOnClickListener {
            listener.onEditItemClick(roles)
        }
        holder.btnBorrar.setOnClickListener {
            listener.onDeleteItemClick(roles)
        }
    }
    inner class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {
        val cvRoles=itemView.findViewById<CardView>(R.id.cvRoles)
        val txtid=itemView.findViewById<TextView>(R.id.txtIdRol)
        val txtNombreRol=itemView.findViewById<TextView>(R.id.txtNombreRol)
        val btnBorrar=itemView.findViewById<Button>(R.id.btnBorrarRol)
    }
}