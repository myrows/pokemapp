package com.example.pokemapp.ui.pokemon

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokemapp.R
import com.example.pokemapp.response.Pokemon
import com.example.pokemapp.viewmodel.PokemonViewModel

class PokemonFragment : Fragment() {
    private lateinit var pokemonAdapter : MyPokemonRecyclerViewAdapter
    private lateinit var pokemonViewModel : PokemonViewModel
    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)

        // Obtenemos la instancia del ViewModel
        pokemonViewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        pokemonAdapter = MyPokemonRecyclerViewAdapter()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                // Esta lineas en Java era : view.setAdapter(pokemonAdapter)
                adapter = pokemonAdapter
            }
        }

        //  Conectamos un observador
        pokemonViewModel.getAllPokemons().observe(viewLifecycleOwner, Observer {
            pokemonAdapter.setData(it)
        })
        return view
    }
}
