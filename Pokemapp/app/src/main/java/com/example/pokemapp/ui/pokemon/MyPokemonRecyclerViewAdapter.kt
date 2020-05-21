package com.example.pokemapp.ui.pokemon


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.pokemapp.R
import com.example.pokemapp.response.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon.view.*
import java.io.File


class MyPokemonRecyclerViewAdapter() : RecyclerView.Adapter<MyPokemonRecyclerViewAdapter.ViewHolder>() {

    private var pokemons : List<Pokemon> = ArrayList<Pokemon>()
    private val mOnClickListener: View.OnClickListener
    lateinit var ctx : Context

    init {
        mOnClickListener = View.OnClickListener { v ->
            val pokemonClicked = v.tag as Pokemon

            var urlId: String = pokemonClicked.url
            var split : List<String> = urlId.split("/")
            var itemId : Int = split[split.size - 2].toInt()

            var intent = Intent ( ctx, PokemonDetailActivity::class.java )
            intent.putExtra("ID", itemId)
            ctx.startActivity( intent )

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_pokemon, parent, false)

        ctx = parent.context

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pokemons[position]
        var urlId: String = item.url
        var split : List<String> = urlId.split("/")
        var itemId : Int = split[split.size - 2].toInt()
        val imageUri: Uri = Uri.fromFile(File("//android_asset/$itemId.png"))
        holder.poTitle.text = item.name
        holder.poImage.load(imageUri)


        holder.mView.tag = pokemons[position]

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = pokemons.size

    fun setData( pokemonsData : List<Pokemon> ) {
        pokemons = pokemonsData

        // Actualizar la interfaz de usuario con los nuevos pokemons
        notifyDataSetChanged()
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val poImage : ImageView = mView.pokemonImg
        val poTitle : TextView = mView.pokemonTitle
    }
}
