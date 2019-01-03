package com.necatisozer.movievault.ui.main

import android.app.Activity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.necatisozer.movievault.data.source.local.objectbox.entity.Movie
import com.necatisozer.movievault.databinding.MovieListItemBinding
import com.squareup.picasso.Picasso

class MoviesListAdapter(private val activity: Activity) :
    RecyclerView.Adapter<MoviesListAdapter.CustomViewHolder>() {
    private var movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesListAdapter.CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = MovieListItemBinding.inflate(layoutInflater, parent, false)
        return CustomViewHolder(itemBinding)
    }

    fun setItems(movies: List<Movie>) {
        val startPosition = this.movies.size
        this.movies.addAll(movies)
        notifyItemRangeChanged(startPosition, movies.size)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun getItem(position: Int): Movie {
        return movies[position]
    }

    override fun onBindViewHolder(holder: MoviesListAdapter.CustomViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class CustomViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val width = displayMetrics.widthPixels

            itemView.layoutParams = RecyclerView.LayoutParams(
                (width * 0.85f).toInt(),
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        }

        fun bindTo(movie: Movie) {
            Picasso.get().load(movie.posterUrl).into(binding.image)
        }
    }
}
