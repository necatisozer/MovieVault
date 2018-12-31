package com.necatisozer.movievault.data.source.remote.tmdb.dto

import com.necatisozer.movievault.data.source.remote.Dto
import com.squareup.moshi.JsonClass
import org.threeten.bp.LocalDate

@JsonClass(generateAdapter = true)
data class MovieResults(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
) : Dto {
    @JsonClass(generateAdapter = true)
    data class Result(
        val adult: Boolean,
        val backdrop_path: String?,
        val genre_ids: List<Int>,
        val id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String?,
        val release_date: LocalDate,
        val title: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
    ) : Dto
}