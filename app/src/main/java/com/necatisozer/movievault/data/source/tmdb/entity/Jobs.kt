package com.necatisozer.movievault.data.source.tmdb.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Jobs(
    val department: String,
    val jobs: List<String>
) : TmdbEntity