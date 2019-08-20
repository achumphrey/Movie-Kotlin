package com.example.moviekotlin.model.moviedetails

import com.google.gson.annotations.SerializedName

data class Genres (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String
)