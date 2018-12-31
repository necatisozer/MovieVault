package com.necatisozer.movievault.data.source.remote.tmdb.dto

import com.necatisozer.movievault.data.source.remote.Dto
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Jobs(
    val department: String,
    val jobs: List<String>
) : Dto