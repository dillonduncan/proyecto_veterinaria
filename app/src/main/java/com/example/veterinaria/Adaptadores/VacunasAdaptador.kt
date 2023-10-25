package com.example.veterinaria.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.veterinaria.DataBase.Entities.Tipos
import com.example.veterinaria.DataBase.Entities.Vacunas
import com.example.veterinaria.R

class VacunasAdaptador(
    val listamsctVacunas:MutableList<Vacunas>,
    val listener:VacunasAdaptadorListener
):RecyclerView.Adapter<VacunasAdaptador.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var vista= LayoutInflater.from(parent.context).inflate(R.layout.ly_mostrar_vacuna,parent,false)
        return ViewHolder(vista)
    }
    override fun getItemCount(): Int =listamsctVacunas.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val vacunas=listamsctVacunas[position]
        holder.txtId.text=vacunas.id.toString()
        holder.txtNombre_Vacuna.text=vacunas.nombre_vacunas
        holder.cvVacunas_masct.setOnClickListener {
            listener.onEditItemClick(vacunas)
        }
        holder.btnBorrar.setOnClickListener{
            listener.onDeleteItemClick(vacunas)
        }
    }
    inner class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {
        val cvVacunas_masct=itemView.findViewById<CardView>(R.id.cvVacuna_masct)
        val txtId=itemView.findViewById<TextView>(R.id.txtID)
        val txtNombre_Vacuna=itemView.findViewById<TextView>(R.id.txtNombre_Vacuna)
        val btnBorrar=itemView.findViewById<Button>(R.id.btnBorrarVacuna)
    }
}