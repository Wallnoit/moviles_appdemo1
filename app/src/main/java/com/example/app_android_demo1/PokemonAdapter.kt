package com.example.app_android_demo1


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_android_demo1.databinding.ItemPockemonBinding
import com.squareup.picasso.Picasso

class PokemonAdapter(var pokemons:List<Pokemon> = emptyList()) :

    RecyclerView.Adapter<PokemonAdapter.PokemonAdapterViewHolder>() {
    lateinit var setOnClickListenerPokemon: (Pokemon) -> Unit

    inner class PokemonAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var binding: ItemPockemonBinding = ItemPockemonBinding.bind(itemView)

        fun bind(pockemon: Pokemon) {
            with(binding)
            {
               txtPokemon.setText(pockemon.name)
                Picasso.get().load(pockemon.imageUrl)
                    .error(R.drawable.notimage)
                    .into(imgPokemon)
                root.setOnClickListener{
                    setOnClickListenerPokemon(pockemon)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pockemon, parent, false)
        return PokemonAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

    override fun onBindViewHolder(holder: PokemonAdapterViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bind(pokemon)
    }


    fun updateListPokemons(pokemons: List<Pokemon>){
        this.pokemons = pokemons
        notifyDataSetChanged()
    }
}
