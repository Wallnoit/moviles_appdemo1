package com.example.app_android_demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app_android_demo1.databinding.ActivityRegistroBinding
import com.example.app_android_demo1.models.Vehiculo
import com.example.app_android_demo1.utils.Constantes

class registro : AppCompatActivity() {

    private lateinit var  binding: ActivityRegistroBinding
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //Inicializar datos
        inicializarDatos()

        //manejar eventos
        eventos()

    }



    private fun inicializarDatos(){
        var bundle = intent.extras
        bundle?.let {
            val vehiculo = bundle.getSerializable(Constantes.KEY_VEHICULO) as Vehiculo
            with(binding){
                btnRegistrar.setText("Actualizar")
                id = vehiculo.id
                edtMarca.setText(vehiculo.marca)
                edtModelo.setText(vehiculo.modelo)
                edtAnio.setText(vehiculo.anio)
            }
        }?:kotlin.run{
            with(binding){
                btnRegistrar.setText("Registrar")

                edtMarca.setText("")
                edtModelo.setText("")
                edtAnio.setText("")
            }

        }

        binding.edtMarca.requestFocus()

    }

    private fun eventos(){
        binding.btnRegistrar.setOnClickListener {
            val marca = binding.edtMarca.text.toString()
            val modelo = binding.edtModelo.text.toString()
            val anio = binding.edtAnio.text.toString()

            if(id==0){
                agregar(vehiculo = Vehiculo(0, marca, modelo, anio.toInt()))
            }else{
                actualizar( vehiculo = Vehiculo(id, marca, modelo, anio.toInt()))
            }

        }
    }

    private fun actualizar(vehiculo: Vehiculo) {


    }

    private fun agregar(vehiculo: Vehiculo) {
    }

}