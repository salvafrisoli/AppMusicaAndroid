package com.example.appmusica.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmusica.Inicial

import com.example.appmusica.MusicaResponse
import com.example.appmusica.R
import com.example.appmusica.ResultAPI

class MusicaAdapter(var items: MutableList<ResultAPI>,private val context: Context) : RecyclerView.Adapter<MusicaViewHolder>() {

    var onItemClick : ((ResultAPI) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_musica, parent, false)
        return MusicaViewHolder(view)
        
    }

    override fun onBindViewHolder(holder: MusicaViewHolder, position: Int) {

        holder.cancion.text = items[position].result.cancion
        holder.artista.text = items[position].result.artista
        //holder.bind(items[position].result.fotoCancion)
        Glide.with(context).load(items[position].result.fotoCancion).into(holder.fotoCancion)

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    fun Update(items_new: MutableList<ResultAPI>){
        items = items_new
        this.notifyDataSetChanged()
    }

}