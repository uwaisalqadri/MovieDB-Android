package com.uwaisalqadri.muvi_app.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.uwaisalqadri.muvi_app.data.source.local.GenreConverters
import com.uwaisalqadri.muvi_app.data.source.remote.response.GenreItem

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
@Entity(tableName = "favorite_movie")
data class MovieEntity(
    val adult: Boolean,
    val backdrop_path: String?,
    val genres: List<GenreItem>?,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
