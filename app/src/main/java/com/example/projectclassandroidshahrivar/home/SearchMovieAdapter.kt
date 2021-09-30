package com.example.projectclassandroidshahrivar.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectclassandroidshahrivar.R
import com.example.projectclassandroidshahrivar.model.Search
import de.hdodenhof.circleimageview.CircleImageView

class SearchMovieAdapter (private val mList:List<Search>,
                          private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<SearchMovieAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMovieAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_rc_searchbox, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchMovieAdapter.MyViewHolder, position: Int) {
        val itemsViewModel = mList[position]
        Glide.with(holder.poster).load(itemsViewModel.Poster).into(holder.poster);
        holder.year.text = "Year : ${itemsViewModel.Year}"
        holder.type.text = "Type : ${itemsViewModel.Type}"
        holder.title.text = "Type : ${itemsViewModel.Title}"

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(itemsViewModel)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val poster : CircleImageView = itemView.findViewById(R.id.img_movie_search)
        val title : TextView = itemView.findViewById(R.id.txt_name_movie_search)
        val type : TextView = itemView.findViewById(R.id.txt_type_movie_search)
        val year : TextView = itemView.findViewById(R.id.txt_year_movie_search)

    }
}

