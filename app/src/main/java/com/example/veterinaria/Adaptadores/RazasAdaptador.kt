package com.example.veterinaria.Adaptadores

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.veterinaria.DataBase.DB
import com.example.veterinaria.DataBase.Entities.Razas
import com.example.veterinaria.DataBase.Entities.Tipos
import com.example.veterinaria.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RazasAdaptador(
    val listamsctRazas:MutableList<Razas>,
    val listener:RazasAdaptadorListener
):RecyclerView.Adapter<RazasAdaptador.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var vista=LayoutInflater.from(parent.context).inflate(R.layout.ly_mostrar_razas,parent,false)
        return ViewHolder(vista)
    }
    override fun getItemCount(): Int =listamsctRazas.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val razas=listamsctRazas[position]
        var tipo:String=""
        //CoroutineScope(Dispatchers.IO).launch {
            //tipo=DB.ObtenerDB(holder.txtIdrz.context).TiposDao().ObtenerNombreTipo_Id(razas.tipo_Raza_id)

            // DB.ObtenerDB(holder.txtIdrz.context).TiposDao().ObtenerNombreTipo_Id(razas.tipo_Raza_id)
        holder.txtIdrz.text=razas.id_raza.toString()
        holder.txtRaza.text=razas.nombre_raza
        holder.txtTipo_Raza.text=razas.tipo_Raza_id.toString()
        holder.cvRaza_msct.setOnClickListener{
            listener.onEditItemClick(razas)
        }
        holder.btnBorrarRz.setOnClickListener {
           /* AlertDialog.Builder(holder.txtIdrz.context)
                .setMessage("¿Desea eliminar la raza ${razas.nombre_raza} ${razas.tipo_Raza}")
                .setPositiveButton("Si"){
                    view,b->*/
                    listener.onDeleteItemClick(razas)
                    //this@RazasAdaptador.notifyItemRangeRemoved(position)
              //  }
              /*  .setNegativeButton("No") {
                    Toast.makeText(, "", Toast.LENGTH_SHORT).show()
                }    */
        }
    }
    inner class ViewHolder (ItemView:View):RecyclerView.ViewHolder(ItemView){
        val cvRaza_msct=itemView.findViewById<CardView>(R.id.cvRaza_Msct)
        val txtIdrz=itemView.findViewById<TextView>(R.id.txtIDRz)
        val txtTipo_Raza=itemView.findViewById<TextView>(R.id.txtNombre_TipoRz)
        val txtRaza=itemView.findViewById<TextView>(R.id.txtRaza)
        val btnBorrarRz=itemView.findViewById<Button>(R.id.btnBorrasRz)
    }
}