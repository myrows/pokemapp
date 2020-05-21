package com.example.pokemapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pokemapp.repository.PokemonRepository
import com.example.pokemapp.response.Pokemon

class PokemonViewModel : ViewModel() {
    var pokemonRepository : PokemonRepository = PokemonRepository()
    lateinit var pokemons : LiveData<List<Pokemon>>

    init {

    }

    fun getAllPokemons () : LiveData<List<Pokemon>> {
        pokemons = pokemonRepository.getAllPokemons()

        return pokemons
    }
}