package com.example.app_android_demo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app_android_demo1.database.AppDatabase
import com.example.app_android_demo1.databinding.ActivityListadoBinding

class listado : AppCompatActivity() {

    private lateinit var binding: ActivityListadoBinding
    private val adapter : VehiculoAdapter by lazy { VehiculoAdapter() }
    private val appDataBase: AppDatabase by lazy { AppDatabase.geInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListadoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //cargar datos
        cargarDatos()

        //cargar eventos
        cargarEventos()

        //cargar adaptador
        cargarAdaptador()



    }

    private fun cargarEventos() {
        binding.floatingActionButton2.setOnClickListener {
            startActivity(Intent(this, registro::class.java))
        }
    }

    private fun cargarDatos() {
        appDataBase.vehiculoDao().getVehiculos().observe(this, { vehiculos ->
            adapter.updateListVehiculos(vehiculos)
        })
    }

    private fun cargarAdaptador() {
        binding.rvListado.adapter = adapter
        }


}