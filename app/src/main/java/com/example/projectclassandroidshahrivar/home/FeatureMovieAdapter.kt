package com.example.projectclassandroidshahrivar.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectclassandroidshahrivar.R
import com.example.projectclassandroidshahrivar.model.Search

class FeatureMovieAdapter(private val mList:List<Search>) : RecyclerView.Adapter<FeatureMovieAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureMovieAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_rc_featuremovie, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeatureMovieAdapter.MyViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        Glide.with(holder.poster).load(itemsViewModel.Poster).into(holder.poster);
        holder.year.text = "Year : ${itemsViewModel.Year}"
        holder.type.text = "Type : ${itemsViewModel.Type}"
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val poster : ImageView = itemView.findViewById(R.id.img_poster)
        val year : TextView = itemView.findViewById(R.id.lbl_year)
        val type : TextView = itemView.findViewById(R.id.lbl_type)

    }
}