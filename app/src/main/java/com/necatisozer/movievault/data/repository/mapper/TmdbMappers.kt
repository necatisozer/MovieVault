package com.necatisozer.movievault.data.repository.mapper

import com.necatisozer.movievault.data.repository.entity.Cast
import com.necatisozer.movievault.data.repository.entity.Crew
import com.necatisozer.movievault.data.repository.entity.Genre
import com.necatisozer.movievault.data.repository.entity.Movie
import com.necatisozer.movievault.data.source.tmdb.TmdbModule
import com.necatisozer.movievault.data.source.tmdb.entity.Credits
import com.necatisozer.movievault.data.source.tmdb.entity.MovieResults

fun MovieResults.Result.mapToMovieEntity() =
    Movie(
        adult = adult,
        backdropUrl = backdrop_path?.let { TmdbModule.getBackdropUrl(it) },
        genres = genre_ids.map { it -> Genre(id = it, name = "") }, // TODO: Get genre strings
        id = id,
        originalLanguage = original_language,
        originalTitle = original_title,
        overview = overview,
        posterUrl = poster_path?.let { TmdbModule.getPosterUrl(it) },
        releaseDate = release_date,
        title = title,
        voteAverage = vote_average,
        voteCount = vote_count
    )

fun MovieResults.mapToMovieList() = results.map { it.mapToMovieEntity() }

fun com.necatisozer.movievault.data.source.tmdb.entity.Movie.mapToMovieEntity() =
    Movie(
        adult = adult,
        backdropUrl = backdrop_path?.let { TmdbModule.getBackdropUrl(it) },
        genres = genres.map { it -> Genre(id = it.id, name = it.name) },
        id = id,
        originalLanguage = original_language,
        originalTitle = original_title,
        overview = overview.let { it } ?: "",
        posterUrl = poster_path?.let { TmdbModule.getPosterUrl(it) },
        releaseDate = release_date,
        title = title,
        voteAverage = vote_average,
        voteCount = vote_count
    )

fun Credits.Cast.mapToCastEntity() = Cast(
    id = id,
    profilePath = profile_path?.let { TmdbModule.getProfileUrl(it) },
    name = name,
    character = character
)

fun Credits.Crew.mapToCrewEntity() = Crew(
    id = id,
    profilePath = profile_path?.let { TmdbModule.getProfileUrl(it) },
    name = name,
    job = job
)

fun Credits.mapToCastAndCrewList() =
    Pair(cast.map { it.mapToCastEntity() }, crew.map { it.mapToCrewEntity() })
