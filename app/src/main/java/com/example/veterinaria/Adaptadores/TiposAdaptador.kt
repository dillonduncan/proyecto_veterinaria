package com.example.veterinaria.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.veterinaria.DataBase.Entities.Tipos
import com.example.veterinaria.R

class TiposAdaptador(
    val listamsctTipos:MutableList<Tipos>,
    val listener:TiposAdaptadorListener
): RecyclerView.Adapter<TiposAdaptador.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var vista= LayoutInflater.from(parent.context).inflate(R.layout.ly_mostrar_tipos,parent,false)
        return ViewHolder(vista)
    }
    override fun getItemCount(): Int = listamsctTipos.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tipos=listamsctTipos[position]
        holder.txtId.text=tipos.id.toString()
        holder.txtNombre_Tipo.text=tipos.nombre
        holder.cvTipos_masct.setOnClickListener {
            listener.onEditItemClick(tipos)
        }
        holder.btnBorrar.setOnClickListener{
            listener.onDeleteItemClick(tipos)
        }
    }
    inner class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {
        val cvTipos_masct=itemView.findViewById<CardView>(R.id.cvTipos_masct)
        val txtId=itemView.findViewById<TextView>(R.id.txtID)
        val txtNombre_Tipo=itemView.findViewById<TextView>(R.id.txtNombre_Tipo)
        val btnBorrar=itemView.findViewById<Button>(R.id.btnBorrar)
    }
}