package com.example.pokemapp.api

import com.example.pokemapp.response.PokemonDetailResponse
import com.example.pokemapp.response.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon")
    fun getAllPokemons() : Call<PokemonResponse>

    @GET("pokemon/{id}")
    fun getPokemonDetail( @Path("id") idPokemon : Int  ) : Call<PokemonDetailResponse>
}