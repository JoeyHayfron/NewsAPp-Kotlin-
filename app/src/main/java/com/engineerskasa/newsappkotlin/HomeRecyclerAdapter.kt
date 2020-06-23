package com.engineerskasa.newsappkotlin

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class HomeRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val headline: TextView = view.findViewById(R.id.homeHeadline)
    val source: TextView = view.findViewById(R.id.homeSource)
    val image: ImageView = view.findViewById(R.id.homeImage)
}

class HomeRecyclerAdapter(private var newsList: List<NewsItem>, private val context: Context) :
    RecyclerView.Adapter<HomeRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.home_item, parent, false)
        return HomeRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (newsList.isNotEmpty()) newsList.size else 0
    }

    fun loadData(data: List<NewsItem>) {
        newsList = data
        notifyDataSetChanged()
    }

    fun getNews(position: Int): NewsItem? {
        return if (newsList.isNotEmpty()) newsList[position] else null
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {
        val newsObject = newsList[position]

        holder.headline.text = newsObject.title
        holder.source.text = newsObject.source
        Picasso.get().load(newsObject.imageUrl)
            .placeholder(R.drawable.news_placeholder)
            .error(R.drawable.news_placeholder)
            .into(holder.image)
    }
}