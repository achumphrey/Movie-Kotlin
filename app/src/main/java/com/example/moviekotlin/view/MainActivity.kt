package com.example.moviekotlin.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviekotlin.Constants
import com.example.moviekotlin.MovieRequest
import com.example.moviekotlin.R
import com.example.moviekotlin.model.moviepopular.MoviePopular
import com.example.moviekotlin.model.moviepopular.Results
import com.example.moviekotlin.network.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
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

                val adapter:MovieAdapter  = MovieAdapter(res, object : OnMovieClickLister{
                    override fun onMovieClick(results: Results) {

                        val intent = Intent(this@MainActivity, DetailActivity::class.java)
                        intent.putExtra(Constants.INTENT_MESSAGE, results.id)
                        startActivity(intent)
                        Log.d("CLICKEDITEM", results.title)
                    }

                })
                rv_list.layoutManager = LinearLayoutManager(this@MainActivity)
                rv_list.adapter = adapter
            }
        })
    }
}
