package com.necatisozer.movievault.data.source.remote.tmdb

import com.necatisozer.movievault.data.source.local.objectbox.entity.Movie
import com.necatisozer.movievault.data.source.remote.tmdb.dto.MovieResults

fun MovieResults.Result.mapToMovieEntity() = Movie(
    adult = adult,
    backdropUrl = backdrop_path?.let { TmdbModule.getBackdropUrl(it) },
    genres = genre_ids.map { it -> it.toString() }, // TODO: Get genre strings
    id = id.toLong(),
    originalLanguage = original_language,
    originalTitle = original_title,
    overview = overview,
    posterUrl = poster_path?.let { TmdbModule.getPosterUrl(it) },
    releaseDate = release_date,
    title = title,
    voteAverage = vote_average,
    voteCount = vote_count
)
