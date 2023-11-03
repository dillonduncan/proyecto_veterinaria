package com.example.veterinaria.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.veterinaria.DataBase.Entities.ControlVacunas
import com.example.veterinaria.R
import com.example.veterinaria.Registro_Mascota_Activity

class ControlesAdaptador(
    val listaControles:MutableList<ControlVacunas>,
    val listener: ControlesAdaptadorListener
):RecyclerView.Adapter<ControlesAdaptador.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var vista=LayoutInflater.from(parent.context).inflate(R.layout.ly_mostrar_control_vacunas,parent,false)
        return ViewHolder(vista)
    }
    override fun getItemCount(): Int =listaControles.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var control=listaControles[position]
        holder.txtId.text=control.id.toString()
        holder.txtVacuna.text=control.vacunaid.toString()
        holder.txtMascota.text=control.mascotaid.toString()
        holder.txtFecha.text=control.fecha
        holder.btnBorrarControl.setOnClickListener {
            listener.onDeleteItemClick(control)
        }
        holder.cvControl.setOnClickListener {
            listener.onEditItemClick(control)
        }
        Registro_Mascota_Activity.lisMascota
    }
    inner class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {
        val cvControl=ItemView.findViewById<CardView>(R.id.cvControles)
        val txtId=ItemView.findViewById<TextView>(R.id.txtIdControl)
        val txtMascota=ItemView.findViewById<TextView>(R.id.txtMascotaControl)
        val txtVacuna=ItemView.findViewById<TextView>(R.id.txtVacunaControl)
        val txtFecha=itemView.findViewById<TextView>(R.id.txtFechaControl)
        val btnBorrarControl=ItemView.findViewById<Button>(R.id.btnBorrarControl)
    }
}