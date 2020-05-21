package com.example.pokemapp.response

import com.example.pokemapp.response.Pokemon

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Pokemon>
)