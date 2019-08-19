package com.example.moviekotlin

import com.example.moviekotlin.model.MoviePopular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieRequest {

    @GET("movie/popular")
    fun getmoviesPopular(@Query("api_key") apiKey : String): Call<MoviePopular>
}