package com.mahdiba97.movies.feature_movies_info.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceBuilder {
    companion object {
        //logger
        private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        //okhttp client
        private val okHttp = OkHttpClient.Builder().addInterceptor(logger)

        fun <T> buildService(api: Class<T>, baseUrl: String): T {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp.build()).baseUrl(baseUrl).build().create(api)
        }

    }
}