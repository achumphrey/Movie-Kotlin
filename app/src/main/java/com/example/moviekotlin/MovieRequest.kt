package com.example.moviekotlin

import com.example.moviekotlin.model.moviedetails.DetailModel
import com.example.moviekotlin.model.moviepopular.MoviePopular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieRequest {

    @GET("movie/popular")
    fun getmoviesPopular(@Query("api_key") apiKey : String): Call<MoviePopular>

    @GET("movie/{movie_id}?api_key=2ca947b36cea2f898bd68e3a64039720")
    fun getMvIdReponse(@Path("movie_id") movie_id: Int): Call<DetailModel>
}