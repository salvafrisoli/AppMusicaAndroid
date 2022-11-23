package com.example.appmusica.adapter

import android.content.Context
import com.example.appmusica.ApiService
import com.example.appmusica.Inicial
import com.example.appmusica.MusicaResponse
import com.example.appmusica.ResultAPI

class Repository {
    suspend fun fetchData(context: Context): ArrayList<ResultAPI> {
        return API.fetchData(context)
    }
}