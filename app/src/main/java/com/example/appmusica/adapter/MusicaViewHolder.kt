package com.example.appmusica.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmusica.R
import com.example.appmusica.databinding.ItemMusicaBinding
import com.squareup.picasso.Picasso

class MusicaViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    val cancion : TextView = itemView.findViewById(R.id.tvCancion)
    val artista : TextView = itemView.findViewById(R.id.tvArtista)
    val fotoCancion : ImageView = itemView.findViewById(R.id.ivMusica)

    private val binding = ItemMusicaBinding.bind(itemView)

    fun bind(image:String){
        Picasso.get().load(image).into(binding.ivMusica)
    }

}