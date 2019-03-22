package com.necatisozer.movievault.domain.entity

data class Crew(
    val id: Int,
    val profilePath: String?,
    val name: String,
    val job: String
) : Entity