package com.example.app_android_demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app_android_demo1.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        imprimirMensaje()

    }

    fun imprimirMensaje() {

        with(binding) {
            btnConfirmar.setOnClickListener {
                val nombre = editNombres.text.toString()
                val apellido = editTextText2.text.toString()
                val mensaje = "Bienvenido $nombre $apellido"
                resultado.text = mensaje
            }
        }



    }
}