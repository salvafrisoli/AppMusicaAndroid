package com.example.appmusica.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmusica.Inicial

import com.example.appmusica.MusicaResponse
import com.example.appmusica.R

class MusicaAdapter(var items: Inicial, context: Context) : RecyclerView.Adapter<MusicaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MusicaViewHolder(layoutInflater.inflate(R.layout.item_musica, parent, false))
        
    }

    override fun onBindViewHolder(holder: MusicaViewHolder, position: Int) {
//        holder.cancion.text = items.Response.hits[position].cancion
        holder.cancion.text = items.response.hits[position].cancion
        holder.cancion.text = items.response.hits[position].artista
    }

    override fun getItemCount(): Int = items.response.hits.size

    fun Update(items_new: Inicial){
        items = items_new
        this.notifyDataSetChanged()
    }

}