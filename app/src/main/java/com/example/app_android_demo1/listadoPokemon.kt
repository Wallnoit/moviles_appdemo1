package com.example.app_android_demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import com.example.app_android_demo1.databinding.ActivityListadoPokemonBinding

class listadoPokemon : AppCompatActivity() {

    private lateinit var binding:ActivityListadoPokemonBinding

    private val adapter : PokemonAdapter by lazy {
        PokemonAdapter()
    }

    private  var listaPokemons:List<Pokemon> = listOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListadoPokemonBinding.inflate(layoutInflater)

        setContentView(binding.root)

        cargarAdaptador()

        adapter.setOnClickListenerPokemon = { pokemon ->
            val pokemonDialog = PokemonDetailsDialog(this, pokemon)
            pokemonDialog.show()
        }

        cargarDatos(binding.search.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    cargarDatos(query!!)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    cargarDatos(newText!!)
                    return true
                }
            }
        ).toString())

    }

    private fun cargarDatos(search: String) {
        listaPokemons = listOf(
            Pokemon("Bulbasaur","https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png"),
            Pokemon("Ivysaur","https://assets.pokemon.com/assets/cms2/img/pokedex/full/002.png"),
            Pokemon("Venusaur","https://assets.pokemon.com/assets/cms2/img/pokedex/full/003.png"),
            Pokemon("Charmander","https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png"),
            Pokemon("Charmeleon","https://assets.pokemon.com/assets/cms2/img/pokedex/full/005.png"),
            Pokemon("Charizard","https://assets.pokemon.com/assets/cms2/img/pokedex/full/006.png"),
            Pokemon("Squirtle","https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png"),
            Pokemon("Wartortle","https://assets.pokemon.com/assets/cms2/img/pokedex/full/008.png"),
            Pokemon("Blastoise","https://assets.pokemon.com/assets/cms2/img/pokedex/full/009.png"),
            Pokemon("Caterpie","https://assets.pokemon.com/assets/cms2/img/pokedex/full/010.png"),)


        println("ME EJECUTE " + search)



        if (search.isEmpty() || search == "kotlin.Unit") {
            println("ME EJECUTEEEEEEE")
            adapter.updateListPokemons(listaPokemons)
            return
        }

         val pokemon = listaPokemons.filter {

            it.name.uppercase().contains(search.uppercase())

        }

        if (pokemon.isEmpty() && search.isNotEmpty()) {
            println("ME EJECUTE ADENTRO")
            adapter.updateListPokemons(emptyList())
            return
        }
        adapter.updateListPokemons(pokemon)
    }

    private fun cargarAdaptador() {

        binding.listadoPockemon.adapter = adapter



    }







}