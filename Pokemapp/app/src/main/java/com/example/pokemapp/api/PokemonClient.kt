package com.example.pokemapp.api

import com.example.pokemapp.common.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonClient {
    private val pokemonService: PokemonService
    private val retrofit: Retrofit

    init {
        // Incluir el interceptor que hemos definido

        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(PokemonInterceptor())

        val cliente = okHttpClientBuilder.build()

        // Construir el cliente de Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(Constantes.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(cliente)
            .build()

        // Instanciamos el servicio de Retrofit a partir del objeto retrofit
        pokemonService = retrofit.create(PokemonService::class.java)
    }

    fun getPokemonService() = pokemonService

    /*private val pokemonService : PokemonService
    private val retrofit : Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(Constantes.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        pokemonService = retrofit.create(PokemonService::class.java)
    }

    fun getPokemonsService() = pokemonService

    private val logging =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)

    private val httpClientBuilder = OkHttpClient.Builder()

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(Constantes.API_BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())

    var retrofit: Retrofit? = null

    fun <S> createServicePokemon(serviceClass: Class<S>?): S? {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .build()
                val requestBuilder = original.newBuilder()
                    .url(url)
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })
        httpClientBuilder.addInterceptor(logging)
        builder.client(httpClientBuilder.build())
        retrofit = builder.build()
        return retrofit?.create(serviceClass)
    }*/
}