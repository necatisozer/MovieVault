package com.necatisozer.movievault.data.source.local.objectbox.entity

import com.necatisozer.movievault.data.source.local.objectbox.DateConverter
import com.necatisozer.movievault.data.source.local.objectbox.StringListConverter
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import org.threeten.bp.LocalDate

@Entity
data class Movie(
    @Id(assignable = true)
    var id: Long = 0,
    val adult: Boolean,
    val backdropUrl: String?,
    @Convert(converter = StringListConverter::class, dbType = String::class)
    val genres: List<String>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterUrl: String?,
    @Convert(converter = DateConverter::class, dbType = Long::class)
    val releaseDate: LocalDate,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)