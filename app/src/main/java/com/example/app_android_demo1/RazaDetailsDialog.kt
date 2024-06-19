package com.example.app_android_demo1

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class RazaDetailsDialog(context: Context, private val name: String, private val image: String) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_pokemon)

        val imagePokemon: ImageView = findViewById(R.id.image_pokemon)
        val textPokemonName: TextView = findViewById(R.id.text_pokemon_name)

        Picasso.get().load(image)
            .error(R.drawable.notimage)
            .into(imagePokemon)

        textPokemonName.text = name
    }
}
