package com.example.app_android_demo1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_android_demo1.databinding.ItemVehiculoBinding
import com.example.app_android_demo1.models.Vehiculo

class VehiculoAdapter(var vehiculos:List<Vehiculo> = emptyList()) :

    RecyclerView.Adapter<VehiculoAdapter.VehiculoAdapterViewHolder>() {
    lateinit var setOnClickListenerEditar: (Vehiculo) -> Unit
    lateinit var setOnClickListenerEliminar: (Vehiculo) -> Unit

    inner class VehiculoAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding: ItemVehiculoBinding = ItemVehiculoBinding.bind(itemView)

        fun bind(vehiculo: Vehiculo) {
            with(binding)
            {
                txtMarca.text = vehiculo.marca
                txtModelo.text = vehiculo.modelo
                txAnio.text = vehiculo.anio.toString()
                btnEdit.setOnClickListener{
                    setOnClickListenerEditar(vehiculo)
                }
                btnDelete.setOnClickListener{
                    setOnClickListenerEliminar(vehiculo)
                }

            }
        }





    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vehiculo, parent, false)
        return VehiculoAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return vehiculos.size
    }

    override fun onBindViewHolder(holder: VehiculoAdapterViewHolder, position: Int) {
        val vehiculo = vehiculos[position]
        holder.bind(vehiculo)
        }


    fun updateListVehiculos(vehiculos: List<Vehiculo>){
        this.vehiculos = vehiculos
        notifyDataSetChanged()
    }
}
