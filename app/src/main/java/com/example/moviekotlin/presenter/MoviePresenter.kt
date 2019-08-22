package com.example.moviekotlin.presenter


import com.example.moviekotlin.common.Constants
import com.example.moviekotlin.model.moviepopular.MoviePopular
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter: BasePresenter<MovieView>(){

    override fun onViewAttached(view: MovieView) {
        super.onViewAttached(view)

        view.showLoading()

        val movieRequest = RetrofitInstance().retrofitInstance.create(MovieRequest::class.java)
        val call = movieRequest.getmoviesPopular(Constants.API_KEY)

        call.enqueue(object : Callback<MoviePopular> {

            override fun onFailure(call: Call<MoviePopular>, t: Throwable) {
            }

            override fun onResponse(call: Call<MoviePopular>, response: Response<MoviePopular>) {

                val res = response.body()
                view.showMoviePopular(res!!)
            }
        })
    }
}
interface MovieView: BasePresenter.View{

    fun showLoading()
    fun showMoviePopular(moviePopular: MoviePopular)

}