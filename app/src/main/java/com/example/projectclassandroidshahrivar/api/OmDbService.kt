package com.example.projectclassandroidshahrivar.api

import com.example.projectclassandroidshahrivar.model.MovieSearch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface OmDbService {
    @GET("/")
    fun searchByTitle(@Query("S") name : String, @Query("apiKey") apiKey:String) : Call<MovieSearch>



    companion object {
        const val API_URL = "https://omdbapi.com"
    }
}