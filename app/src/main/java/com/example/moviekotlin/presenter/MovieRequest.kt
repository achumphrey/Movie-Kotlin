package com.example.moviekotlin.presenter

import com.example.moviekotlin.model.moviedetails.DetailModel
import com.example.moviekotlin.model.moviepopular.MoviePopular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRequest {

    @GET("movie/popular")
    fun getmoviesPopular(@Query("api_key") apiKey : String): Call<MoviePopular>

    @GET("movie/{movie_id}")
    fun getMvIdReponse(@Path("movie_id") movie_id: Int,
                       @Query("api_key") apiKey: String): Call<DetailModel>
}