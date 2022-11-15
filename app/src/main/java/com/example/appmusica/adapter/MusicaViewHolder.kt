package com.example.appmusica.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmusica.R
import com.example.appmusica.databinding.ItemMusicaBinding

class MusicaViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    val cancion : TextView = itemView.findViewById(R.id.tvCancion)
    val artista : TextView = itemView.findViewById(R.id.tvArtista)
    //val foto = view.findViewById<ImageView>(R.id.ivMusica)
}