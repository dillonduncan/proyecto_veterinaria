package com.example.veterinaria.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.veterinaria.DataBase.DB
import com.example.veterinaria.DataBase.Entities.Mascotas
import com.example.veterinaria.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MascotaAdaptador(
    val listamascota:MutableList<Mascotas>,
    val listener:MascotaAdaptadorListener
):RecyclerView.Adapter<MascotaAdaptador.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var vista=LayoutInflater.from(parent.context).inflate(R.layout.ly_mostra_mascotas,parent,false)
        return ViewHolder(vista)
    }
    override fun getItemCount(): Int =listamascota.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mascota=listamascota[position]
        var tipo="0"
        var raza="0"
       /* CoroutineScope(Dispatchers.IO).launch {
            tipo= DB.ObtenerDB(holder.txtTipo.context).TiposDao().ObtenerNombreTipo_id(mascota.tipo_mascota_id)
            raza=DB.ObtenerDB(holder.txtTipo.context).RazaDao().ObtenerNombreRaza_Id(mascota.raza_mascota_id)
        }*/
        holder.txtIdmsct.text=mascota.id.toString()
        holder.txtNombre.text=mascota.nombre_mascota
        holder.txtTipo.text=mascota.tipo_mascota_id.toString()
        holder.txtRaza.text=mascota.raza_mascota_id.toString()
        holder.txtFecha_Nacimiento.text=mascota.fecha_nacimiento
        holder.txtDueño.text=mascota.dueño_mascota.toString()
        holder.cvMascotas.setOnClickListener {
            listener.onEditItemClick(mascota)
        }
        holder.btnBorrarMsct.setOnClickListener {
            listener.onDeleteItemClick(mascota)
        }
    }
    inner class ViewHolder(ItemView: View):RecyclerView.ViewHolder(ItemView) {
        val cvMascotas=itemView.findViewById<CardView>(R.id.cvMascotas)
        val txtIdmsct=itemView.findViewById<TextView>(R.id.txtID)
        val txtNombre=itemView.findViewById<TextView>(R.id.txtNombre_Msct)
        val txtTipo=itemView.findViewById<TextView>(R.id.txtTipo)
        val txtRaza=itemView.findViewById<TextView>(R.id.txtRaza)
        val txtFecha_Nacimiento=itemView.findViewById<TextView>(R.id.txtFecha_Nacimiento)
        val txtDueño=itemView.findViewById<TextView>(R.id.txtDueño_Mascota)
        val btnBorrarMsct=itemView.findViewById<Button>(R.id.btnBorrar)
        }
    }
