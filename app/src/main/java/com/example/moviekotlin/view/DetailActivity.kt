package com.example.moviekotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.moviekotlin.common.Constants
import com.example.moviekotlin.presenter.MovieRequest
import com.example.moviekotlin.R
import com.example.moviekotlin.model.moviedetails.DetailModel
import com.example.moviekotlin.presenter.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.row.rt_bar
import kotlinx.android.synthetic.main.row.tv_overview
import kotlinx.android.synthetic.main.row.tv_title
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        val movieId = intent.getIntExtra(Constants.INTENT_MESSAGE, 0)

        val movieRequest = RetrofitInstance().retrofitInstance.create(MovieRequest::class.java)
        val call = movieRequest.getMvIdReponse(movieId, Constants.API_KEY)

        call.enqueue(object : Callback<DetailModel> {
            override fun onResponse(call: Call<DetailModel>, response: Response<DetailModel>) {

                val res = response.body()

                Log.d("MainActivity", res!!.title)

                tv_title.text = res.title
                tv_overview.text = res.overview
                rt_bar.rating = res.vote_average.toFloat()
                Picasso.get().load("https://image.tmdb.org/t/p/w185" + res.poster_path).into(img_view)
                tv_vote_avg.text = res.vote_average.toString() + "/10"
            }

            override fun onFailure(call: Call<DetailModel>, t: Throwable) {

            }
          })
    }
}