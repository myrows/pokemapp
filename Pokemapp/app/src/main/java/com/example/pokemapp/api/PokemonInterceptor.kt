package com.example.pokemapp.api

import okhttp3.Interceptor
import okhttp3.Response

class PokemonInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        // Añadimos parámetros a la URL de la cadena que recibimos (chain)
        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .build()

        var request = chain.request()

        request = request?.newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(request)

    }
}