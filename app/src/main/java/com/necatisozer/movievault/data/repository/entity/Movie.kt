package com.necatisozer.movievault.data.repository.entity

import org.threeten.bp.LocalDate

data class Movie(
    val adult: Boolean,
    val backdropUrl: String?,
    val genres: List<String>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterUrl: String?,
    val releaseDate: LocalDate,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
) : Entity