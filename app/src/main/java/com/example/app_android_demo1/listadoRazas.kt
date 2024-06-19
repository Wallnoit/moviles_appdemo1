package com.example.app_android_demo1

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.app_android_demo1.databinding.ActivityListadoRazasBinding
import org.json.JSONException

class listadoRazas : AppCompatActivity() {
    private lateinit var binding: ActivityListadoRazasBinding
    private val razaAdapter: RazaAdapter by lazy {
        RazaAdapter{ raza -> cargarImageRaza(raza) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListadoRazasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        cargarDatosApi()
    }

    private fun cargarDatosApi() {
        val cola = Volley.newRequestQueue(this)
        val url = "https://dog.ceo/api/breeds/list/all"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                try {


                    val keys = response.getJSONObject("message").keys()
                    val listaRazas : MutableList<listaRazas> = ArrayList()

                    while (keys.hasNext()){
                        val key = keys.next()
                        listaRazas.add(listaRazas(key))
                    }

                    binding.rvlistadoRazas.setHasFixedSize(true)
                    binding.rvlistadoRazas.layoutManager = LinearLayoutManager(this)
                    razaAdapter.razaAdapter(this, listaRazas)
                    binding.rvlistadoRazas.adapter = razaAdapter

                }catch (e: JSONException){
                    Log.d("Error", e.message.toString()) //
                }

            },
            { error ->
                println("Error: ${error.message}")
            }

        )
        cola.add(jsonObjectRequest)
    }

    private fun cargarImageRaza(raza:String){

        val cola = Volley.newRequestQueue(this)
        val url = "https://dog.ceo/api/breed/${raza.trim()}/images/random"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                try {

                    val imageUrl = response.getString("message")

                    onRazaClicked(raza, imageUrl)

                }catch (e: JSONException){
                    Log.d("Error", e.message.toString()) //
                }

            },
            { error ->
                println("Error: ${error.message}")
            }

        )
        cola.add(jsonObjectRequest)

    }

    private fun onRazaClicked(raza:String, image: String) {
        
        val pokemonDialog = RazaDetailsDialog(this, raza, image)
        pokemonDialog.show()
    }

}