package com.example.pokemapp.ui.pokemon

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.api.load
import com.example.pokemapp.R
import com.example.pokemapp.api.PokemonClient
import com.example.pokemapp.common.MyApp
import com.example.pokemapp.response.PokemonDetailResponse
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class PokemonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        var pokemonService = PokemonClient().getPokemonService()
        var dataIntent = intent.extras!!.get("ID").toString()

        var call : Call<PokemonDetailResponse> = pokemonService.getPokemonDetail(dataIntent.toInt())

        call.enqueue(object : Callback<PokemonDetailResponse> {
            override fun onResponse(
                call: Call<PokemonDetailResponse>,
                response: Response<PokemonDetailResponse>
            ) {
                if( response.isSuccessful ) {
                    var myPokemon : PokemonDetailResponse? = response.body()

                    nameDetail.text = myPokemon?.name
                    height.text = myPokemon?.height.toString() + " m"
                    weight.text = myPokemon?.weight.toString() + " kg"
                    experience.text = myPokemon?.base_experience.toString() + " xp"
                    val imageUri: Uri = Uri.fromFile(File("//android_asset/$dataIntent.png"))
                    pokemonDetailImage.load(imageUri)
                }
            }

            override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error de conexión", Toast.LENGTH_LONG).show()
            }
        } )
    }
}
