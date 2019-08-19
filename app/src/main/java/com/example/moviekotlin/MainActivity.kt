package com.example.moviekotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.moviekotlin.model.MoviePopular
import com.example.moviekotlin.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieRequest = RetrofitInstance().retrofitInstance.create(MovieRequest::class.java)
        val call = movieRequest.getmoviesPopular(Constants.API_KEY)
        call.enqueue(object : Callback<MoviePopular>{
            override fun onFailure(call: Call<MoviePopular>, t: Throwable) {
            }

            override fun onResponse(call: Call<MoviePopular>, response: Response<MoviePopular>) {
                val res = response.body()
                Log.d("MainActivity", res!!.results[0].title)
            }
        });
    }
}
