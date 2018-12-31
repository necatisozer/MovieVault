package com.necatisozer.movievault.data.source.remote.tmdb

import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.data.source.remote.tmdb.dto.MovieResults

fun MovieResults.Result.mapToMovieEntity(): Movie {
    return Movie(
        adult = adult,
        backdropUrl = backdrop_path, // TODO: Concat base url
        genres = genre_ids.map { it -> it.toString() }, // TODO: Get genre strings
        id = id,
        originalLanguage = original_language,
        originalTitle = original_title,
        overview = overview,
        posterUrl = poster_path, // TODO: Concat base url
        releaseDate = release_date,
        title = title,
        voteAverage = vote_average,
        voteCount = vote_count
    )
}
