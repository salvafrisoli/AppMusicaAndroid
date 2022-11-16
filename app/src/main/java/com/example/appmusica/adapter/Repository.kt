package com.example.appmusica.adapter

import android.content.Context
import com.example.appmusica.ApiService
import com.example.appmusica.Inicial
import com.example.appmusica.MusicaResponse

class Repository {
    suspend fun fetchData(context: Context): Inicial {
        return API.fetchData(context)
    }
}