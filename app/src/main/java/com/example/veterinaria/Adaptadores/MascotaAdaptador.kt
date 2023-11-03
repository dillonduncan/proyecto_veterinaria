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
import com.example.veterinaria.Registro_Mascota_Activity
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
        holder.txtIdmsct.text="ID: "+mascota.id.toString()
        holder.txtNombre.text="Nombre: "+mascota.nombre_mascota
        holder.txtTipo.text="Tipo: "+ mascota.tipo_mascota_id //Registro_Mascota_Activity.tipoMascota
        holder.txtRaza.text="Raza: "+ mascota.raza_mascota_id //Registro_Mascota_Activity.razaMascota
        holder.txtFecha_Nacimiento.text="Nacimiento: "+mascota.fecha_nacimiento
        holder.txtDueño.text="Dueño: "+mascota.dueño_mascota
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