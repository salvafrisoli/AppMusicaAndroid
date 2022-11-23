package com.example.appmusica.adapter

import android.content.Context
import android.util.Log
import com.example.appmusica.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {
    companion object{
    val BASE_URL = "https://api.genius.com/"

    suspend fun fetchData(context: Context) : ArrayList<ResultAPI> {
        Log.d("API-DEMO", "Call to API Started")

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(ApiService::class.java)

        val result = api.getSongsByName("eminem").execute()

        return if (result.isSuccessful) {
            Log.d("API-DEMO", "Successfull")
            result.body()!!.response.hits
        } else {
            Log.d("API-DEMO", "Error")
            ArrayList<ResultAPI>()
        }
    }

    }
}