package com.necatisozer.movievault.data.source.tmdb.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
) : TmdbEntity {
    @JsonClass(generateAdapter = true)
    data class Crew(
        val credit_id: String,
        val department: String,
        val gender: Int?,
        val id: Int,
        val job: String,
        val name: String,
        val profile_path: String?
    ) : TmdbEntity

    @JsonClass(generateAdapter = true)
    data class Cast(
        val cast_id: Int,
        val character: String,
        val credit_id: String,
        val gender: Int?,
        val id: Int,
        val name: String,
        val order: Int,
        val profile_path: String?
    ) : TmdbEntity
}