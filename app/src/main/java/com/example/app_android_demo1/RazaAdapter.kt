package com.example.app_android_demo1

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.app_android_demo1.databinding.ItemRazaBinding

class RazaAdapter(
    private val onItemClickListener: (String) -> Unit
) : RecyclerView.Adapter<RazaAdapter.RazaAdapterViewHolder>() {

    var listaRazas: MutableList<listaRazas> = mutableListOf()
    lateinit var context: Context

    inner class RazaAdapterViewHolder(val binding: ItemRazaBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(raza: listaRazas) {
            binding.btnRaza.text = raza.raza
            binding.btnRaza.setOnClickListener {
                onItemClickListener(raza.raza)
            }
        }
    }

    fun razaAdapter(context: Context, listaRazas: MutableList<listaRazas>) {
        this.context = context
        this.listaRazas = listaRazas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RazaAdapterViewHolder {
        return RazaAdapterViewHolder(ItemRazaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listaRazas.size
    }

    override fun onBindViewHolder(holder: RazaAdapterViewHolder, position: Int) {
        holder.bind(listaRazas[position])
    }



    fun updateListRazas(newList: MutableList<listaRazas>) {
        this.listaRazas = newList
        notifyDataSetChanged()
    }
}
