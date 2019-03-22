package com.necatisozer.movievault.domain.entity

import org.threeten.bp.LocalDate

data class Movie(
    var id: Int = 0,
    val adult: Boolean,
    val backdropUrl: String?,
    val genres: List<Genre>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterUrl: String?,
    val releaseDate: LocalDate,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
) : Entity

data class Genre(
    val id: Int,
    val name: String
) : Entity