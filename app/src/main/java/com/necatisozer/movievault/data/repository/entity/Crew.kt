package com.necatisozer.movievault.data.repository.entity

data class Crew(
    val id: Int,
    val profilePath: String?,
    val name: String,
    val job: String
) : Entity