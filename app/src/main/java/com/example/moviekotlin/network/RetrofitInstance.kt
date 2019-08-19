package com.example.moviekotlin.network

import com.example.moviekotlin.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    val retrofitInstance: Retrofit

    get() {

            val loggingInterceptor = HttpLoggingInterceptor()
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor((loggingInterceptor))
                .build()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return   Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
}