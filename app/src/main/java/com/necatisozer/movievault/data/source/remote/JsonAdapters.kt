package com.necatisozer.movievault.data.source.remote

import com.necatisozer.movievault.data.source.remote.tmdb.dto.Movie
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class DateAdapter {
    @ToJson
    fun toJson(date: LocalDate): String {
        return DateTimeFormatter.ISO_LOCAL_DATE.format(date)
    }

    @FromJson
    fun fromJson(date: String): LocalDate {
        return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE)
    }
}

class StatusAdapter {
    @ToJson
    fun toJson(status: Movie.Status): String {
        return status.value
    }

    @FromJson
    fun fromJson(status: String): Movie.Status {
        return Movie.Status.valueOf(status)
    }
}