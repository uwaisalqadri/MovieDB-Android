package com.uwaisalqadri.muvi_app.domain.model

/**
 * Created by Uwais Alqadri on April 06, 2021
 */
data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String?,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String?
)