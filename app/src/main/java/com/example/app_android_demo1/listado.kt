package com.example.app_android_demo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.app_android_demo1.database.AppDatabase
import com.example.app_android_demo1.databinding.ActivityListadoBinding
import com.example.app_android_demo1.models.Vehiculo
import com.example.app_android_demo1.utils.Constantes
import java.util.concurrent.Executors

class listado : AppCompatActivity() {

    private lateinit var binding: ActivityListadoBinding
    private val adapter: VehiculoAdapter by lazy {
        VehiculoAdapter().apply {
            setOnClickListenerEditar = { it?.let { vehiculo ->
                val intent = Intent(this@listado, registro::class.java)
                intent.putExtra(Constantes.KEY_VEHICULO, vehiculo)
                startActivity(intent)
             } }

            setOnClickListenerEliminar = { vehiculo ->
                eliminarVehiculo(vehiculo)

            }
        }
    }
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

    private fun editarVehiculo(vehiculo: Vehiculo) {
        val intent = Intent(this, registro::class.java)
        intent.putExtra(Constantes.KEY_VEHICULO, vehiculo)
        startActivity(intent)
    }

    private fun eliminarVehiculo(vehiculo: Vehiculo) {
        Executors.newSingleThreadExecutor().execute {
            appDataBase.vehiculoDao().delete(vehiculo)
            runOnUiThread {
                Toast.makeText(this, "Vehiculo Eliminado", Toast.LENGTH_LONG).show()
            }
        }


    }
}