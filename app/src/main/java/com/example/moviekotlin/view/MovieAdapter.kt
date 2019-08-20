package com.example.moviekotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.example.moviekotlin.R
import com.example.moviekotlin.model.moviepopular.MoviePopular
import com.example.moviekotlin.model.moviepopular.Results
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row.view.*

class MovieAdapter (private val moviePopular: MoviePopular, private val listener: OnMovieClickLister)
    : Adapter<MovieViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false))
    }

    override fun getItemCount(): Int {
        return moviePopular.results.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.tvTitle.text = moviePopular.results[position].title
        holder.overView.text = moviePopular.results[position].overview
        holder.ratingBar.rating = moviePopular.results[position].vote_average.toFloat()
        Picasso.get().load("http://image.tmdb.org/t/p/w185"
                + moviePopular.results[position]
            .poster_path).into(holder.imgView)

        holder.bind(moviePopular.results[position], listener)
    }
}

class MovieViewHolder (view: View): RecyclerView.ViewHolder(view){

    fun bind (results: Results, listener: OnMovieClickLister){
        itemView.setOnClickListener{
            listener.onMovieClick(results)
        }
    }

    val tvTitle = view.tv_title
    val imgView = view.iv_image
    val overView = view.tv_overview
    val ratingBar = view.rt_bar
}

interface OnMovieClickLister{

    fun onMovieClick(results: Results)
}