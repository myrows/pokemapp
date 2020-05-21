package com.example.pokemapp.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.pokemapp.api.PokemonClient
import com.example.pokemapp.api.PokemonService
import com.example.pokemapp.common.MyApp
import com.example.pokemapp.response.Pokemon
import com.example.pokemapp.response.PokemonResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepository {

    var pokemonService : PokemonService
    var pokemonClient : PokemonClient = PokemonClient()
    var pokemons : MutableLiveData<List<Pokemon>>

    init {
        pokemonService = pokemonClient.getPokemonService()
        pokemons = MutableLiveData<List<Pokemon>>()
    }

    fun getAllPokemons() : MutableLiveData<List<Pokemon>> {

        val call : Call<PokemonResponse> = pokemonService.getAllPokemons()
        call.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                if( response.isSuccessful ) {
                    pokemons.value = response.body()?.results
                }
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la conexión", Toast.LENGTH_LONG).show()
            }
        })

        return pokemons
    }
}